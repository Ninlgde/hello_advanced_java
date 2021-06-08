package com.ninlgde.algorithm.pq;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/9/21 5:13 PM
 */
public class IndexMinPQ<E extends Comparable<E>> extends IndexHeap<E> {

    public IndexMinPQ(int capacity) {
        super(capacity);
    }

    @Override
    protected boolean compare(int i, int j) {
        return compareTo(i, j) > 0;
    }

    public void insert(int k, E item) {
        push(k, item);
    }

    public int delMin() {
        return pop();
    }

    public E min() {
        return head();
    }

    public int minIndex() {
        return headIndex();
    }

    public static void main(String[] args) {
        IndexMinPQ<String> maxPQ = new IndexMinPQ<>(100);
        maxPQ.insert(1, "P");
        maxPQ.insert(10, "Q");
        StdOut.println(maxPQ.min());
        maxPQ.insert(3, "E");
        StdOut.println(maxPQ.delMin());

        maxPQ.insert(2, "X");
        StdOut.println(maxPQ.min());
        maxPQ.change(10, "B");
        StdOut.println(maxPQ.min());
        maxPQ.insert(3, "A");
        maxPQ.insert(4, "M");
        StdOut.println(maxPQ.delMin());
        StdOut.println(maxPQ.min());

        maxPQ.insert(6, "P");
        maxPQ.insert(8, "L");
        maxPQ.delete(10);
        StdOut.println(maxPQ.min());
        maxPQ.insert(33, "E");
        StdOut.println(maxPQ.delMin());
    }
}
