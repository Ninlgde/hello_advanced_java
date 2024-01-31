package com.ninlgde.advanced.forkjoin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author: ninlgde
 * @date: 2020/4/29 01:08
 */
public class FibTask extends ForkJoinTask<Long> {

    static final int threshold = 13;
    volatile long number; // arg/result

    FibTask(long n) {
        number = n;
    }

    @Override
    public Long getRawResult() {
        return number;
    }

    @Override
    protected void setRawResult(Long value) {
        this.number = value;
    }

    @Override
    protected boolean exec() {
        long n = number;
        if (n <= threshold) // granularity ctl
            number = seqFib(n);
        else {
            FibTask f1 = new FibTask(n - 1);
            FibTask f2 = new FibTask(n - 2);
            f1.fork();
            f2.fork();
            number = f1.join() + f2.join();
        }
        return true;
    }

    static long seqFib(long n) {
        if (n <= 1)
            return n;
        else
            return seqFib(n - 1) + seqFib(n - 2);
    }

    public static void main(String[] args) {
        List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9);
        numberList.parallelStream().forEach(System.out::println);
        int n = 45;
        ForkJoinPool pool = new ForkJoinPool();
        FibTask task = new FibTask(n);

        long startTime = System.currentTimeMillis();
        Long fib = pool.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("fork/join : fib = " + fib + ", cost time = " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        fib = seqFib(n);
        endTime = System.currentTimeMillis();
        System.out.println("normal : fib = " + fib + ", cost time = " + (endTime - startTime) + "ms");
    }
}
