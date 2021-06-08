package com.ninlgde.netty.reactor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: ninlgde
 * @date: 2020/5/1 20:03
 */
public class SubReactorThreadFactory implements ThreadFactory {
    public final AtomicInteger numCreated = new AtomicInteger();

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "SubReactorThread-" + numCreated.incrementAndGet());
    }
}
