package com.ninlgde.concurrency;

import java.util.concurrent.atomic.AtomicLong;

public class Counter {
    AtomicLong count = new AtomicLong(0);
    long count2 = 0L;
    long count3 = 0L;
    long count4 = 0L;

    private Lock lock = new Lock();
    private FairLock fairLock = new FairLock();

    void add(long value) {
        this.count.addAndGet(value);
    }

    void add2(long value) {
        try {
            lock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count2 += value;
        lock.unlock();
    }

    synchronized void add3(long value) {
        count3 += value;
    }

    void add4(long value) {
        try {
            fairLock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count4 += value;
        fairLock.unlock();
    }

}
