package com.ninlgde.jcip.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: ninlgde
 * @date: 11/24/20 9:31 PM
 */
public class ThreadPerTaskWebServer extends WebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = () -> {
                try {
                    handleRequest(connection);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            new Thread(task).start();
        }
    }
}
