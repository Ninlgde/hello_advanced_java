package com.ninlgde.advanced.future;

import io.netty.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class NettyFutureDemo {
    private static CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        EventExecutorGroup group = new DefaultEventExecutorGroup(4);
        try {
            Future<Integer> future = group.submit(() -> {
                System.out.println("执行耗时操作...");
                timeConsumingOperation();
                return 100;
            });
            future.addListener((FutureListener<Object>) objectFuture -> {
                System.out.println("计算结果:" + objectFuture.get());//<2>
                latch.countDown();
            });
            System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + "ms");
            latch.await();
        } finally {
            group.shutdownGracefully();
        }
    }

    static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
