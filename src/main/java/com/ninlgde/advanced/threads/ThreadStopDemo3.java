package com.ninlgde.advanced.threads;

import java.util.concurrent.TimeUnit;

/**
 * @author ninlgde
 * @date 2022/4/18 15:50
 */
public class ThreadStopDemo3 {
    public static void main(String[] args) throws Exception {
        MyTask task = new MyTask();
        Thread thread1 = new Thread(task, "MyTask1");
        Thread thread2 = new Thread(task, "MyTask2");
        thread1.start();
        thread2.start();
        TimeUnit.MILLISECONDS.sleep(5);
        thread1.interrupt();
        thread2.interrupt();
    }

    private static class MyTask implements Runnable {

        private volatile long count = 0L;

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 线程启动");
            // 通过 Thread.interrupted 和 interrupt 配合来控制线程终止
            while (!Thread.interrupted()) {
                System.out.println(count++);
            }
            System.out.println(Thread.currentThread().getName() + " 线程终止");
        }
    }
}
