package com.ninlgde.advanced.bytecode;

import java.util.ArrayList;
import java.util.List;

public class TestSynchronized {
    private final Object lock = new Object();
    public void foo() {
        synchronized (lock) {
            bar();
        }
    }

    public void bar() { }

    synchronized public void testMe() {
    }

    public void testWait() throws InterruptedException {
        synchronized (lock) {
            lock.wait();
        }
    }

    public long add(long a, long b, int c) {
        return a++ + ++b + c++;
    }
}
