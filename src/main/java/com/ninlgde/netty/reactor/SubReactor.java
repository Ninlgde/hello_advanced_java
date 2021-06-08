package com.ninlgde.netty.reactor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: ninlgde
 * @date: 2020/5/1 19:55
 */
public class SubReactor {
    private static final int NCPU = Runtime.getRuntime().availableProcessors() >> 2;
    private static final ExecutorService service = Executors.newFixedThreadPool(NCPU, new SubReactorThreadFactory());
    private static final LinkedBlockingQueue<Channel> queue = new LinkedBlockingQueue<>();

    private SubReactor() {
        // 构造工作线程
        for (int i = 0; i < NCPU;  i++)
            service.submit(new SubReactorThread());
    }

    private static class Holder {
        protected static SubReactor instance = new SubReactor();
    }

    public static SubReactor getInstance() {
        return Holder.instance;
    }

    public void submit(Channel channel) {
        queue.add(channel);
    }

    public LinkedBlockingQueue<Channel> getQueue() {
        return queue;
    }
}
