package com.ninlgde.concurrency;

import java.util.ArrayList;
import java.util.List;

public class ReentrantFairLock {
    private volatile boolean isLocked = false;
    private volatile Thread lockingThread = null;
    private final List<QueueObject> waitingThreads = new ArrayList<>();
    private int counter = 0;

    public void lock()
            throws InterruptedException {
        QueueObject queueObject = new QueueObject();
        if (Thread.currentThread() != lockingThread) {
            boolean isLockedForThisThread = true;
            synchronized (this) {
//                System.out.println(Thread.currentThread().getName() + " add to waiting list");
                waitingThreads.add(queueObject);
            }

            while (isLockedForThisThread) {
                synchronized (this) {
                    isLockedForThisThread = isLocked || waitingThreads.get(0) != queueObject;
                    if (!isLockedForThisThread) {
                        isLocked = true;
                        waitingThreads.remove(queueObject);
                        lockingThread = Thread.currentThread();
                        counter++;
                        return;
                    }
                }
                try {
                    queueObject.doWait();
                } catch (InterruptedException e) {
                    synchronized (this) {
                        waitingThreads.remove(queueObject);
                    }
                    counter=0;
                    throw e;
                }
            }
        }
        counter++;
    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        if (--counter == 0) {
            isLocked = false;
            lockingThread = null;
            if (waitingThreads.size() > 0) {
                waitingThreads.get(0).doNotify();
            }
        }
    }
}