package com.ninlgde.netty.reactor;

import com.ninlgde.netty.nio.MultiplexerTimeServer;

import java.io.IOException;

public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        MainReactor timeServer = new MainReactor(port);
        new Thread(timeServer, "NIO-MainReactor-001").start();
    }
}
