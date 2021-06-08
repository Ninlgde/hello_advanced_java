package com.ninlgde.jcip.webserver;

import java.net.Socket;

/**
 * @author: ninlgde
 * @date: 11/24/20 9:28 PM
 */
public class WebServer {
    public static void handleRequest(Socket connection) throws InterruptedException {
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName());
        System.out.println(connection);
    }
}
