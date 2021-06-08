package com.ninlgde.algorithm.sort;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/7/21 3:13 PM
 */
public class MergeBU extends Merge {

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz+sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    public static void main(String[] args) {
        String[] a = {"E", "E", "G", "M", "R", "A", "C", "E", "R", "T"};
        sort(a);
        show(a);
        StdOut.println(isSorted(a));
    }
}
