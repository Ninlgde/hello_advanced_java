package com.ninlgde.concurrency;

public class ThreadLocalExample {

    public static class MyRunnable implements Runnable {

        private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        @Override
        public void run() {
            try {
                threadLocal.set((int) (Math.random() * 100D));

                try {
                    Thread.sleep(threadLocal.get() * 50);
                } catch (InterruptedException ignored) {
                }

                System.out.println(Thread.currentThread().getName() + ' ' + threadLocal.get());
            } finally {
                // key是weak reference 如果线程长期运行且用完ThreadLocal不删除可能导致内存泄漏
                threadLocal.remove();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance, "1");
        Thread thread2 = new Thread(sharedRunnableInstance, "2");

        thread1.start();
        thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate
    }
}