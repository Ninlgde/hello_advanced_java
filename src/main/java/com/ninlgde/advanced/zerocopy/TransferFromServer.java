package com.ninlgde.advanced.zerocopy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TransferFromServer {
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
        TransferFromServer dns = new TransferFromServer();
        dns.mySetup();
        dns.transferFromData();
    }

    private void transferFromData() {
        try {
            String fname = "/tmp/test-file-out.txt";

            while (true) {
                SocketChannel conn = listener.accept();
                System.out.print("Accepted : " + conn);
                conn.configureBlocking(true);

                FileChannel fc = new RandomAccessFile(fname, "rw").getChannel();
                long start = System.nanoTime();

                // 有个不好的地方就是需要预先知道文件大小
                long curnset = fc.transferFrom(conn, 0, 1818018);
                MappedByteBuffer mbuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
                ByteBuffer buffer = mbuffer.duplicate();
                System.out.println(" Read: " + curnset + " and time taken in nano--" + (System.nanoTime() - start));
                fc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
