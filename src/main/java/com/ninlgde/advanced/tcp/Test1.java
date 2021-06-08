package com.ninlgde.advanced.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Test1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 9999));
        OutputStream out = socket.getOutputStream();
        byte[] bytes= new byte[100 * 1024];
        out.write(bytes);
        System.in.read();
    }
}
