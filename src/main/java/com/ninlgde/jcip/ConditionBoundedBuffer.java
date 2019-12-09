package com.ninlgde.jcip;

import com.ninlgde.jcip.annotations.GuardedBy;
import com.ninlgde.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public class ConditionBoundedBuffer<T> {
    private final int BUFFER_SIZE = 1000;
    protected final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpyt = lock.newCondition();
    @GuardedBy("lock")
    private final T[] items = (T[]) new Object[BUFFER_SIZE];
    @GuardedBy("lock")
    private int tail, head, count;

    public void put(T x)
            throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[tail] = x;
            if (++tail == items.length)
                tail = 0;
            ++count;
            notEmpyt.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take()
            throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpyt.await();
            T x = items[head];
            items[head] = null;
            if (++head == items.length)
                head = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
