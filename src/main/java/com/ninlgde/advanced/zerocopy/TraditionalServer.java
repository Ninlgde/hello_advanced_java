package com.ninlgde.advanced.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TraditionalServer {
    ServerSocketChannel listener = null;

    protected void mySetup() {
        InetSocketAddress listenAddr = new InetSocketAddress(8877);

        try {
            listener = ServerSocketChannel.open();
            ServerSocket ss = listener.socket();
            ss.setReuseAddress(true);
            ss.bind(listenAddr);
            System.out.println("Listening on port : " + listenAddr.toString());
        } catch (IOException e) {
            System.out.println("Failed to bind, is port : " + listenAddr.toString()
                    + " already in use ? Error Msg : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TraditionalServer server = new TraditionalServer();
        server.mySetup();
        server.readData();
    }

    private void readData() {
        ByteBuffer dst = ByteBuffer.allocate(4096);
        try {
            while (true) {
                SocketChannel conn = listener.accept();
                System.out.print("Accepted : " + conn);
                conn.configureBlocking(true);
                long start = System.nanoTime();
                int nread = 0, read = 0;
                while (read != -1) {
                    try {
                        nread += read;
                        read = conn.read(dst);
                    } catch (IOException e) {
                        e.printStackTrace();
                        read = -1;
                    }
                    dst.rewind();
                }
                System.out.println(" Read: " + nread + " and time taken in nano--" + (System.nanoTime() - start));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
