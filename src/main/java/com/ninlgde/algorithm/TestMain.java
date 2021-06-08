package com.ninlgde.algorithm;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/1/21 5:51 PM
 */
public class TestMain {

    public static String exR1(int n) {
        if (n <= 0) return "";
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }

    public static void main(String[] args) {
        StdOut.println(exR1(6));
        StdOut.println(1+2+"3");
    }
}
