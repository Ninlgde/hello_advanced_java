package com.ninlgde.algorithm.pq;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ninlgde
 * @date: 2/9/21 11:06 AM
 */
public abstract class IndexHeap<E extends Comparable<E>> {
    protected int size;
    protected int capacity;
    protected int[] pq;
    protected int[] qp;
    protected E[] heap;

    public IndexHeap(int capacity) {
        heap = (E[]) new Comparable[capacity];
        pq = new int[capacity];
        qp = new int[capacity];
        this.capacity = capacity;
        this.size = 0;
        for (int i = 0; i < capacity; i++)
            qp[i] = -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    public void push(int k, E item) {
        qp[k] = size;
        pq[size] = k;
        heap[k] = item;
        swim(size++);
    }

    public E head() {
        return heap[pq[0]];
    }

    public int pop() {
        int indexOfMin = pq[0];
        exch(0, --size);
        sink(0);
        heap[pq[size]] = null;
        qp[pq[size]] = -1;
        return indexOfMin;
    }

    public int headIndex() {
        return pq[0];
    }

    public void change(int k, E item) {
        heap[k] = item;
        swim(qp[k]);
        sink(qp[k]);
    }

    public void delete(int k) {
        int index = qp[k];
        exch(index, --size);
        swim(index);
        sink(index);
        heap[k] = null;
        qp[k] = -1;
    }

    protected int compareTo(int i, int j) {
        return heap[pq[i]].compareTo(heap[pq[j]]);
    }

    protected abstract boolean compare(int i, int j);

    protected void exch(int i, int j) {
        int tt = pq[i];
        pq[i] = pq[j];
        pq[j] = tt;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k > 0 && compare(k >> 1, k)) {
            exch(k >> 1, k);
            k >>= 1;
        }
    }

    private void sink(int k) {
        while ((k << 1) < size) {
            int j = k << 1;
            if (j < size - 1 && compare(j, j + 1)) j++;
            if (!compare(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
}
