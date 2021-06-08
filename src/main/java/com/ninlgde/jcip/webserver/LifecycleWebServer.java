package com.ninlgde.jcip.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Logger;

/**
 * @author: ninlgde
 * @date: 11/25/20 11:13 AM
 */
public class LifecycleWebServer extends WebServer {

    private final ExecutorService exec = Executors.newFixedThreadPool(100);
    private static final Logger log = Logger.getAnonymousLogger();

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        int handled = 0;
        while (!exec.isShutdown()) {
            try {
                final Socket connection = socket.accept();
                exec.execute(() -> {
                    try {
                        handleRequest(connection);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (RejectedExecutionException e) {
                if (!exec.isShutdown())
                    log.info("task submission rejected" + e.getMessage());
            }
            handled += 1;
            if (handled >= 5) {
                stop();
            }
        }
    }

    public void stop() {
        System.out.println("LifecycleWebServer.stop");
        exec.shutdownNow();
    }

    public static void main(String[] args) throws IOException {
        LifecycleWebServer server = new LifecycleWebServer();
        server.start();
    }
}
