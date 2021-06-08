package com.ninlgde.advanced.zerocopy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class TransferToClient {

    public static void main(String[] args) throws IOException {
        TransferToClient sfc = new TransferToClient();
        sfc.testSendfile();
    }

    public void testSendfile() throws IOException {
        String host = "localhost";
        int port = 8877;
        SocketAddress sad = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();
        sc.connect(sad);
        sc.configureBlocking(true);

        String fname = "/tmp/test-file.txt";

        FileChannel fc = new FileInputStream(fname).getChannel();
        long start = System.nanoTime();

        // 核心的硬盘读取和数据发送逻辑
        long curnset = fc.transferTo(0, fc.size(), sc);

        System.out.println("Zero-copy-sendfile: total bytes transferred--" + curnset + " and time taken in nano--" + (System.nanoTime() - start));
        fc.close();
    }
}
