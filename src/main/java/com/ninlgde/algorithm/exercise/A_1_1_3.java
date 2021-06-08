package com.ninlgde.algorithm.exercise;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/18/21 10:39 AM
 */
public class A_1_1_3 {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);

        if (a == b && b == c)
            StdOut.println("equal");
        else
            StdOut.println("not equal");
    }
}
