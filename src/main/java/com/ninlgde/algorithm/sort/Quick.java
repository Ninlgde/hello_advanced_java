package com.ninlgde.algorithm.sort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

/**
 * @author: ninlgde
 * @date: 2/7/21 6:27 PM
 */
public class Quick extends BaseSort {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    protected static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
//        show(a);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    protected static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v))
                if (i == hi) break;
            while (less(v, a[--j]))
                if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void sort(Object[] a, Comparator c) {
        StdRandom.shuffle(a);
        sort(a, c, 0, a.length - 1);
    }

    protected static void sort(Object[] a, Comparator c, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, c, lo, hi);
//        show(a);
        sort(a, c, lo, j - 1);
        sort(a, c, j + 1, hi);
    }

    protected static int partition(Object[] a, Comparator c, int lo, int hi) {
        int i = lo, j = hi + 1;
        Object v = a[lo];
        while (true) {
            while (less(c, a[++i], v))
                if (i == hi) break;
            while (less(c, v, a[--j]))
                if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        String[] a = {"C", "A"};
//        String[] a = {"Q", "U", "I", "C", "K", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(a);
        show(a);
        StdOut.println(isSorted(a));
    }
}
