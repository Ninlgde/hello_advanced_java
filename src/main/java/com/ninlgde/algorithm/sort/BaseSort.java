package com.ninlgde.algorithm.sort;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;


/**
 * @author: ninlgde
 * @date: 2/7/21 10:26 AM
 */
public class BaseSort {

    protected static boolean less(Comparable v, Comparable w) {
//        StdOut.println("less called");
        return v.compareTo(w) < 0;
    }

    protected static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }

    protected static void exch(Comparable[] a, int i, int j) {
//        StdOut.println("exch called");
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected static void exch(Object[] a, int i, int j) {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    protected static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    protected static boolean isSorted(Object[] a, Comparator c) {
        for (int i = 1; i < a.length; i++) {
            if (less(c, a[i], a[i - 1])) return false;
        }
        return true;
    }
}
