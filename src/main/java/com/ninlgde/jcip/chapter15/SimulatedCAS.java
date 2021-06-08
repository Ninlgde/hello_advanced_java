package com.ninlgde.jcip.chapter15;

import com.ninlgde.jcip.annotations.ThreadSafe;

/**
 * @author: ninlgde
 * @date: 1/29/21 6:44 PM
 */
@ThreadSafe
public class SimulatedCAS {
    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue)
            value = newValue;
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return (expectedValue == compareAndSwap(expectedValue, newValue));
    }
}
