package com.ninlgde.concurrency;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReentrantFairLockTest {

    private ReentrantFairLock lock = new ReentrantFairLock();

    private void lockOuter() throws InterruptedException {
        lock.lock();
        String name = Thread.currentThread().getName();
        System.out.println(name + " enter outer");
        lockInner();
        lock.unlock();
        System.out.println(name + " leave outer");
    }

    private void lockInner() throws InterruptedException {
        lock.lock();
        String name = Thread.currentThread().getName();
        System.out.println(name + " enter inner");
        lock.unlock();
        System.out.println(name + " leave inner");
    }

    @Test
    public void lock() throws InterruptedException {
        lockOuter();
    }

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                lockOuter();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void concurrentLock() throws InterruptedException {
        Thread thread1 = new Thread(new MyRunnable(), "Thread 1");
        Thread thread2 = new Thread(new MyRunnable(), "Thread 2");
        Thread thread3 = new Thread(new MyRunnable(), "Thread 3");
        Thread thread4 = new Thread(new MyRunnable(), "Thread 4");
        Thread thread5 = new Thread(new MyRunnable(), "Thread 5");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
    }
}