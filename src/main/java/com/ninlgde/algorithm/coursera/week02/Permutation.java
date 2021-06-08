package com.ninlgde.algorithm.coursera.week02;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 3/8/21 7:56 PM
 */
public class Permutation {

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        int n = Integer.parseInt(args[0]);

        for (int i = 0; i < n; i++) {
            StdOut.println(rq.dequeue());
        }
    }
}
