package com.ninlgde.jcip.chapter14.boundedbuf;

import com.ninlgde.jcip.annotations.ThreadSafe;

/**
 * @author: ninlgde
 * @date: 1/27/21 10:57 AM
 */
@ThreadSafe
public class GrumpyBoundedBuffer<V>  extends BaseBoundedBuffer<V> {

    protected GrumpyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws BufferFullException {
        if (isFull())
            throw new BufferFullException();
        doPut(v);
    }

    public synchronized V take() throws BufferEmptyException {
        if (isEmpty())
            throw new BufferEmptyException();
        return doTake();
    }
}
