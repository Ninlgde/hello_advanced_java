package com.ninlgde.advanced.threads;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ninlgde
 * @date 2022/4/18 11:30
 */
public class CallableDemo {
    public static void main(String[] args) {

//        BigDecimal CHIP_PER_100_DOLLAR = new BigDecimal("9450000000");
//        BigDecimal dollar = new BigDecimal("0.1");
//        long a = dollar.divide(BigDecimal.valueOf(100), 0, RoundingMode.CEILING).multiply(CHIP_PER_100_DOLLAR).longValue();
//        System.out.println(a);

        Callable<Long> callable = new MyThread();
        FutureTask<Long> future = new FutureTask<>(callable);
        new Thread(future, "Callable 线程").start();
        try {
            System.out.println("任务耗时：" + (future.get() / 1000000) + "毫秒");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class MyThread implements Callable<Long> {

        private int ticket = 10000;

        @Override
        public Long call() {
            long begin = System.nanoTime();
            while (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + " 卖出了第 " + ticket + " 张票");
                ticket--;
            }

            long end = System.nanoTime();
            return (end - begin);
        }

    }
}
