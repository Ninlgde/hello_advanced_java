package com.ninlgde.advanced.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TraditionalClient {

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 8877;
        SocketAddress sad = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();
        sc.connect(sad);
        sc.configureBlocking(true);

        String fname = "/tmp/test-file.txt";

        FileInputStream inputStream = new FileInputStream(fname);
        long start = System.nanoTime();
        byte[] b = new byte[4096];
        long read, total = 0;

        // 核心的硬盘读取和数据发送逻辑 -- 开始
        while ((read = inputStream.read(b)) >= 0) {
            total = total + read;
            sc.write(ByteBuffer.wrap(b));
        }
        // 核心的硬盘读取和数据发送逻辑 -- 结束

        System.out.println("Traditional: total bytes transferred--" + total + " and time taken in nano--" + (System.nanoTime() - start));
    }
}
