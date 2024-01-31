package com.ninlgde.advanced.forkjoin;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<BigInteger> {

    private static final long serialVersionUID = 4828818665955149519L;

    /**
     * 每个任务最多允许计算的数字个数阈值，超过这个阈值，任务进行拆分
     */
    private static final BigInteger THRESHOLD = BigInteger.valueOf(1000);

    /**
     * 起始值
     */
    private final BigInteger startNumber;

    /**
     * 结束值
     */
    private final BigInteger endNumber;

    public SumTask(BigInteger startNumber, BigInteger endNumber) {
        this.startNumber = startNumber;
        this.endNumber = endNumber;
    }

    @Override
    protected BigInteger compute() {
        if (startNumber.compareTo(endNumber) > 0) {
            System.out.println("start number should be smaller than end number");
            return BigInteger.ZERO;
        }
        if (endNumber.subtract(startNumber).compareTo(THRESHOLD) < 0) {
            return this.getCount(startNumber, endNumber);
        } else {
//            long mid = (startNumber + endNumber) / 2;
            BigInteger mid = startNumber.add(endNumber).divide(BigInteger.valueOf(2));
            SumTask subTask1 = new SumTask(startNumber, mid);
            SumTask subTask2 = new SumTask(mid.add(BigInteger.ONE), endNumber);
            subTask1.fork();
            subTask2.fork();

            return subTask1.join().add(subTask2.join());
        }
    }

    /**
     * 普通累加执行方法
     *
     * @param start 起始数
     * @param end   结束数
     * @return 累加和
     */
    protected BigInteger getCount(BigInteger start, BigInteger end) {
        BigInteger sum = BigInteger.ZERO;
        for (long i = start.longValue(); i <= end.longValue(); i++) {
            sum = sum.add(BigInteger.valueOf(i));
        }

        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        BigInteger start = BigInteger.ONE;
        BigInteger end = BigInteger.valueOf(346348942L);
        SumTask task = new SumTask(start, end);

        long startTime = System.currentTimeMillis();
        BigInteger sum = forkJoinPool.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("fork/join : sum = " + sum + ", cost time = " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        BigInteger sum2 = task.getCount(start, end);
        endTime = System.currentTimeMillis();
        System.out.println("normal : sum = " + sum2 + ", cost time = " + (endTime - startTime) + "ms");
    }
}
