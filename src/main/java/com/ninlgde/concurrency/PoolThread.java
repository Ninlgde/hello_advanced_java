package com.ninlgde.concurrency;

public class PoolThread extends Thread {

    private BlockingQueue<Runnable> taskQueue;
    private volatile boolean isStopped = false;

    public PoolThread(BlockingQueue<Runnable> queue) {
        taskQueue = queue;
    }

    public void run() {
        while (!isStopped()) {
            try {
                Runnable runnable = taskQueue.dequeue();
                runnable.run();
            } catch (Exception e) {
                // 写日志或者报告异常,
                // 但保持线程池运行.
            }
        }
    }

    public synchronized void toStop() {
        isStopped = true;
        this.interrupt(); // 打断池中线程的 dequeue() 调用.
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}