package com.ninlgde.jcip.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: ninlgde
 * @date: 11/24/20 9:49 PM
 */
public class TaskExecutionWebServer extends WebServer {

    private static final int NTHERADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(NTHERADS);

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
            exec.execute(task);
        }
    }
}
