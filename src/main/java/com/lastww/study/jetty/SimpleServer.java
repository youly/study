package com.lastww.study.jetty;

import org.eclipse.jetty.server.Server;

/**
 * Created by liuweiwei on 15-1-27.
 * see http://www.eclipse.org/jetty/documentation/current/embedding-jetty.html
 */
public class SimpleServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8088);
        server.start();

        server.dumpStdErr();
        server.join();

    }
}
