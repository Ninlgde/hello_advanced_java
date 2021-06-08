package com.ninlgde.jcip.chapter15;

/**
 * @author: ninlgde
 * @date: 1/29/21 6:47 PM
 */
public class CasCounter {
    private SimulatedCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        } while (v != value.compareAndSwap(v, v + 1));
        return v + 1;
    }
}
