package com.ninlgde.algorithm.pq;

/**
 * @author: ninlgde
 * @date: 2/9/21 10:18 AM
 */
public abstract class Heap<E extends Comparable<E>> {

    private E[] heap;
    private int capacity;
    private int size;

    public Heap() {
        capacity = 16;
        heap = (E[]) new Comparable[capacity];
        size = 0;
    }

    public Heap(int capacity) {
        this.capacity = capacity;
        heap = (E[]) new Comparable[capacity];
        size = 0;
    }

    public Heap(E[] a) {
        this.capacity = a.length;
        this.heap = a;
        this.size = a.length - 1;
    }

    public Heap(Heap h) {
        this.heap = (E[]) h.heap;
        this.capacity = h.capacity;
        this.size = h.size;
    }

    protected int compareTo(int i, int j) {
        return heap[i].compareTo(heap[j]);
    }

    protected abstract boolean compare(int i, int j);

    protected void exch(int i, int j) {
        E t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
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
            if (j < size-1 && compare(j, j + 1)) j++;
            if (!compare(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    protected void resize(int k) {
        E[] newHeap = (E[]) new Comparable[k];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }

    public void push(E v) {
        if (size == capacity-1) {
            resize(2 * capacity);
        }
        heap[size] = v;
        swim(size++);
    }

    public E pop() {
        E item = heap[0];
        exch(0, --size);
        heap[size + 1] = null;
        sink(0);
        if (size > 0 && size == capacity / 4) {
            resize(capacity / 2);
        }
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
