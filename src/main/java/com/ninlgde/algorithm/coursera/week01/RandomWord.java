package com.ninlgde.algorithm.coursera.week01;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/**
 * @author: ninlgde
 * @date: 2/25/21 5:25 PM
 */
public class RandomWord {
    public static void main(String[] args) {
        String result = null;
        int cnt = 0;
        while (!StdIn.isEmpty()) {
            String a = StdIn.readString();
            if (StdRandom.bernoulli(1.0 / ++cnt))
                result = a;
        }

        StdOut.println(result);
    }
}
