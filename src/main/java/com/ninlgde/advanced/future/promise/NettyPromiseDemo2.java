package com.ninlgde.advanced.future.promise;

import io.netty.util.concurrent.*;

/**
 * @author: ninlgde
 * @date: 2020/5/6 11:25
 */
public class NettyPromiseDemo2 {

    public static void main(String[] args) {
        EventExecutor executor = new DefaultEventExecutor();
        try {
            Promise<Integer> promise = new DefaultPromise<>(executor);

            // 下面给这个 promise 添加两个 listener
            promise.addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("任务结束，结果：" + future.get());
                } else {
                    System.out.println("任务失败，异常：" + future.cause());
                }
            }).addListener(future -> System.out.println("任务结束，balabala..."));

            // 提交任务到线程池，五秒后执行结束，设置执行 promise 的结果
            executor.submit(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 设置 promise 的结果
                promise.setFailure(new RuntimeException());
//            promise.setSuccess(123456);
            });

            // main 线程阻塞等待执行结果
            try {
//            promise.await(); // 不抛异常
                promise.sync(); // throw exception
            } catch (Exception e) {
                System.out.println("exception");
                e.printStackTrace();
            }
        } finally {
            executor.shutdownGracefully();
        }
    }
}
