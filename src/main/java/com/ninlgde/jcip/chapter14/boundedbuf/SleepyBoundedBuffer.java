package com.ninlgde.jcip.chapter14.boundedbuf;

/**
 * @author: ninlgde
 * @date: 1/27/21 11:30 AM
 */
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    private final long SLEEP_GRANULARITY = 100;

    protected SleepyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public void put(V v) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isFull()) {
                    doPut(v);
                    return;
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }

    public V take() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isEmpty())
                    return doTake();
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }
}
