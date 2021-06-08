package com.ninlgde.jcip.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: ninlgde
 * @date: 11/24/20 9:27 PM
 */
public class SingleThreadWebServer extends WebServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket connection = socket.accept();
            handleRequest(connection);
        }
    }
}
