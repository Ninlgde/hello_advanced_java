package com.ninlgde.advanced.future.promise;

import io.netty.util.concurrent.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class NettyPromiseDemo {

    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        EventExecutorGroup group = new DefaultEventExecutorGroup(4);
        Promise<Integer> promise = group.next().newPromise();
        Promise<Integer> promise2 = group.next().newPromise();
        group.submit(() -> {
            System.out.println("在回调中执行耗时操作...");
            timeConsumingOperation();
            promise.setSuccess(100);
        });
        promise.addListener(future -> {
            System.out.println("计算结果1:" + future.get());//<2>
            System.out.println("在回调的回调中执行耗时操作...");
            timeConsumingOperation();
            promise2.setSuccess((Integer) future.get() + 100);
        });
        promise2.addListener(future -> {
            System.out.println("计算结果2:" + future.get());
            group.shutdownGracefully();
        });
        System.out.println("主线程运算耗时:" + (System.currentTimeMillis() - l) + "ms");
//        new CountDownLatch(1).await();
    }

    static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
