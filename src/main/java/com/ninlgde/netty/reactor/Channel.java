package com.ninlgde.netty.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author: ninlgde
 * @date: 2020/5/1 20:10
 */
public class Channel {
    private final SocketChannel socket;

    public Channel(SocketChannel sc) {
        this.socket = sc;
    }

    public SocketChannel getSocket() {
        return socket;
    }

    public int read(ByteBuffer readBuffer) throws IOException {
        return socket.read(readBuffer);
    }

    public void write(String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            socket.write(writeBuffer);
        }
    }
}
