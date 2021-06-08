package com.ninlgde.concurrency;

import org.junit.Test;

import static org.junit.Assert.*;

public class LockTest {

    private Lock lock = new Lock();

    private void lockOuter() throws InterruptedException {
        lock.lock();
        System.out.println("enter outer");
        lockInner();
        lock.unlock();
        System.out.println("leave outer");
    }

    private void lockInner() throws InterruptedException {
        lock.lock();
        System.out.println("enter inner");
        lock.unlock();
        System.out.println("leave inner");
    }

    @Test
    public void lock() throws InterruptedException {
        lockOuter();
    }
}