package com.ninlgde.concurrency;

import org.junit.Test;

import static org.junit.Assert.*;

public class FairLockTest {

    private FairLock lock = new FairLock();

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
}