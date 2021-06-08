package com.ninlgde.concurrency;

/**
 * 不可重入锁
 * 参考LockTest
 */
public class Lock {

    private boolean isLocked = false;
    private Thread lockingThread = null;

    public synchronized void lock()
            throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
        lockingThread = Thread.currentThread();
    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException(
                    "Calling thread has not locked this lock");
        }
        isLocked = false;
        lockingThread = null;
        notify();
    }
}

