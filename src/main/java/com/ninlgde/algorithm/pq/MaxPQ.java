package com.ninlgde.algorithm.pq;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/9/21 10:13 AM
 */
public class MaxPQ<E extends Comparable<E>> extends Heap<E> {

    @Override
    protected boolean compare(int i, int j) {
        return compareTo(i, j) < 0;
    }

    public void insert(E v) {
        push(v);
    }

    public E delMax() {
        return pop();
    }

    public static void main(String[] args) {
        MaxPQ<String> maxPQ = new MaxPQ<>();
        maxPQ.insert("P");
        maxPQ.insert("Q");
        maxPQ.insert("E");
        maxPQ.insert("P");
        maxPQ.insert("Q");
        maxPQ.insert("E");
        maxPQ.insert("P");
        maxPQ.insert("Q");
        maxPQ.insert("E");
        maxPQ.insert("P");
        maxPQ.insert("Q");
        maxPQ.insert("E");
        maxPQ.insert("P");
        maxPQ.insert("Q");
        maxPQ.insert("E");
        maxPQ.insert("P");
        maxPQ.insert("Q");
        maxPQ.insert("E");
        maxPQ.insert("P");
        maxPQ.insert("Q");
        maxPQ.insert("E");
        maxPQ.insert("P");
        maxPQ.insert("Q");
        maxPQ.insert("E");
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());
        StdOut.println(maxPQ.delMax());

        maxPQ.insert("X");
        maxPQ.insert("A");
        maxPQ.insert("M");
        StdOut.println(maxPQ.delMax());

        maxPQ.insert("P");
        maxPQ.insert("L");
        maxPQ.insert("E");
        StdOut.println(maxPQ.delMax());
    }
}
