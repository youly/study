package com.lastww.study.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuweiwei on 14-11-27.
 */
public class ZookeeperEventContainer implements Watcher {

    public static final int SESSION_TIMEOUT = 10000;

    private ZooKeeper zooKeeper;

    private CountDownLatch countDownLatch;

    private static ZookeeperEventContainer container = new ZookeeperEventContainer();

    public static ZookeeperEventContainer getInstance() {
        return container;
    }

    public ZookeeperEventContainer() {
        try {
            this.zooKeeper = createZooKeeper();
            this.monitor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ZooKeeper createZooKeeper() throws IOException, InterruptedException {

        System.out.println("about to create zookeeper");
        ZooKeeper newZooKeeper = new ZooKeeper("127.0.0.1:2181", SESSION_TIMEOUT, this);
        countDownLatch = new CountDownLatch(1);
        countDownLatch.await(SESSION_TIMEOUT, TimeUnit.MILLISECONDS);
        System.out.println("connection establed, session id:" + newZooKeeper.getSessionId());
        return newZooKeeper;
    }

    public void monitor() {

        //更新数据并注册watcher
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


    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {

        System.out.println("received event:" + event.toString());

        switch (event.getType()) {
            case None:
                switch (event.getState()) {
                    case Expired:
                        System.out.println("expired:" + this.zooKeeper.getSessionId());
                        try {
                            this.zooKeeper.close();
                            this.zooKeeper = createZooKeeper();
                            this.monitor();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case Disconnected:
                        System.out.println("disconnected:" + this.zooKeeper.getSessionId());
                        try {
                            this.zooKeeper.close();
                            this.zooKeeper = createZooKeeper();
                            this.monitor();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case SyncConnected:
                        countDownLatch.countDown();
                        break;
                }
                break;
            case NodeChildrenChanged:
                //节点删除、创建同时会促发上层节点NodeChildrenChanged事件，因此可忽略
                //case NodeDeleted:
                //case NodeCreated:
            case NodeDataChanged:
                if (!this.zooKeeper.getState().isAlive()) {
                    try {
                        this.zooKeeper = createZooKeeper();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                this.monitor();
                break;
        }



    }

    public static void main(String[] args) {

        ZookeeperEventContainer container = ZookeeperEventContainer.getInstance();

        System.out.println("container start");

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关闭连接、测试session expired
        try {
            ZooKeeper oldZooKeeper = new ZooKeeper("127.0.0.1:2181", 10000, null, container.getZooKeeper().getSessionId(), null);
            System.out.println("close old zookeeper:" + oldZooKeeper.getSessionId());
            oldZooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true);
    }
}
