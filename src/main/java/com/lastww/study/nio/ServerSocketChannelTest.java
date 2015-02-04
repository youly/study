package com.lastww.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by liuweiwei on 15-1-26.
 * 多线程accept
 */
public class ServerSocketChannelTest {

    ServerSocketChannel serverSocketChannel;

    public ServerSocketChannelTest() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(true);
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.bind(new InetSocketAddress(8088));
    }

    public void accept() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        System.out.println();
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        buf.put(("Thread name:" + Thread.currentThread().getName() + ", your ip and port:" + socketChannel.getRemoteAddress() + "\n").getBytes());
                        buf.flip();
                        while (buf.hasRemaining()) {
                            socketChannel.write(buf);
                        }
                        socketChannel.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        ServerSocketChannelTest test = new ServerSocketChannelTest();
        for (int i = 0; i < 4; i++) {
            test.accept();
        }
        System.out.println("server start");
    }
}
