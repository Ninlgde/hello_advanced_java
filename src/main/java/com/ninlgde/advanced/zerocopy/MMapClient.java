package com.ninlgde.advanced.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class MMapClient {

    public static void main(String[] args) throws IOException {
        MMapClient client = new MMapClient();
        client.mmap();
    }

    public void mmap() throws IOException {
        String host = "localhost";
        int port = 8877;
        SocketAddress sad = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();
        sc.connect(sad);
        sc.configureBlocking(true);

        String fname = "/tmp/test-file.txt";

        FileChannel fc = new FileInputStream(fname).getChannel();
        long start = System.nanoTime();

        MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
        sc.write(buffer);

        System.out.println("Zero-copy-mmap: total bytes transferred--" + fc.size() + " and time taken in nano--" + (System.nanoTime() - start));
        fc.close();
    }
}
