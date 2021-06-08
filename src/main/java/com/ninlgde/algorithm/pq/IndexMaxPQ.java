package com.ninlgde.algorithm.pq;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/9/21 5:13 PM
 */
public class IndexMaxPQ<E extends Comparable<E>> extends IndexHeap<E> {

    public IndexMaxPQ(int capacity) {
        super(capacity);
    }

    @Override
    protected boolean compare(int i, int j) {
        return compareTo(i, j) < 0;
    }

    public void insert(int k, E item) {
        push(k, item);
    }

    public int delMax() {
        return pop();
    }

    public E max() {
        return head();
    }

    public int maxIndex() {
        return headIndex();
    }

    public static void main(String[] args) {
        IndexMaxPQ<String> maxPQ = new IndexMaxPQ<>(100);
        maxPQ.insert(1, "P");
        maxPQ.insert(10, "Q");
        StdOut.println(maxPQ.max());
        maxPQ.insert(3, "E");
        StdOut.println(maxPQ.delMax());

        maxPQ.insert(2, "X");
        StdOut.println(maxPQ.max());
        maxPQ.change(2, "B");
        StdOut.println(maxPQ.max());
        maxPQ.insert(3, "A");
        maxPQ.insert(4, "M");
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.max());

        maxPQ.insert(6, "P");
        maxPQ.insert(8, "L");
        StdOut.println(maxPQ.max());
        maxPQ.delete(6);
        StdOut.println(maxPQ.max());
        maxPQ.insert(33, "E");
        StdOut.println(maxPQ.delMax());
    }
}
