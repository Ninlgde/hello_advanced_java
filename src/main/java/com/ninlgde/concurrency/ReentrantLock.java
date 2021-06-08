package com.ninlgde.concurrency;

public class ReentrantLock {

    private boolean isLocked = false;
    private Thread lockingThread = null;
    private int counter = 0;

    public synchronized void lock()
            throws InterruptedException {
        if (lockingThread != Thread.currentThread()) {
//            System.out.println(Thread.currentThread().getName() + " add to waiting list");
            while (isLocked) {
                wait();
            }
            isLocked = true;
            lockingThread = Thread.currentThread();
        }
        counter++;
    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException(
                    "Calling thread has not locked this lock");
        }
        if (--counter == 0) {
            isLocked = false;
            lockingThread = null;
            notify();
        }
    }
}
