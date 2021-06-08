package com.ninlgde.netty.reactor;


public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        for (int i = 0; i < 100; i++)
            new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001").start();
    }
}
