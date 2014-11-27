package com.lastww.study.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * Created by liuweiwei on 14-11-27.
 */
public class ZookeeperEventContainer implements Watcher {

    private ZooKeeper zooKeeper;

    private static ZookeeperEventContainer container;

    public static ZookeeperEventContainer getInstance() {
        synchronized (ZookeeperEventContainer.class) {
            if (container == null) {
                container = new ZookeeperEventContainer();
            }
        }
        return container;
    }

    public ZookeeperEventContainer() {
        try {
            this.zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000, this);
            this.monitor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void monitor() {
        try {
            // 监听根节点数据变更、创建、删除
            this.zooKeeper.getData("/root", true, null);
            // 监听子节点删除、子节点创建、删除
            List<String> children = this.zooKeeper.getChildren("/root", true);
            for (String child : children) {
                this.zooKeeper.getData("/root/" + child, true, null);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void process(WatchedEvent event) {
        System.out.println(event.toString());
        this.monitor();
    }

    public static void main(String[] args) {
        ZookeeperEventContainer container = ZookeeperEventContainer.getInstance();
        System.out.println("container start");
        while (true);
    }
}
