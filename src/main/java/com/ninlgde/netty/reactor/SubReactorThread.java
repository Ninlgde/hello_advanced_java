package com.ninlgde.netty.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: ninlgde
 * @date: 2020/5/1 20:04
 */
public class SubReactorThread implements Runnable {

    private Selector selector;

    private Map<SocketChannel, Channel> channels;

    private volatile boolean stop;

    public SubReactorThread() {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        channels = new HashMap<>();
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
//                System.out.println(Thread.currentThread().getName() + " poll channel");
                Channel channel = SubReactor.getInstance().getQueue().poll(1000, TimeUnit.MILLISECONDS);
                if (channel != null) {
                    System.out.println(Thread.currentThread().getName() + " get channel");
                    channel.getSocket().register(selector, SelectionKey.OP_READ, channel);
//                    channel.getSocket().register(selector, SelectionKey.OP_WRITE);
                    channels.put(channel.getSocket(), channel);
                }
                if (channels.isEmpty())
                    continue;
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }


    private void handleInput(SelectionKey key)
            throws IOException {
        if (key.isValid()) {
            if (key.isReadable()) {
                System.out.println(Thread.currentThread().getName() + " readable");
                SocketChannel sc = (SocketChannel) key.channel();
                Channel channel = (Channel) key.attachment();
                ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
                int readBytes = channel.read(readBuffer);
                System.out.println(Thread.currentThread().getName() + " read: " + readBytes);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println(Thread.currentThread().getName() + "The time server receive order: " + body);
                    String currentTime = " QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    channel.write(currentTime);
                } else if (readBytes < 0) {
                    key.cancel();
                    sc.close();
                    channels.remove(sc);
                    System.out.println(Thread.currentThread().getName() + " channels : " + channels.size());
                } else {
                    ;// do noting
                }
            }
        }
    }
}
