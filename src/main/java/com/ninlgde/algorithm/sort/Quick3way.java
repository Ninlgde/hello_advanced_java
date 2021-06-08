package com.ninlgde.algorithm.sort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

/**
 * @author: ninlgde
 * @date: 2/8/21 3:44 PM
 */
public class Quick3way extends BaseSort {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    protected static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo + 1;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0)
                exch(a, lt++, i++);
            else if (cmp > 0)
                exch(a, i, gt--);
            else
                i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void sort(Object[] a, Comparator c) {
        StdRandom.shuffle(a);
        sort(a, c, 0, a.length - 1);
    }

    protected static void sort(Object[] a, Comparator c, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Object v = a[lo];
        int i = lo + 1;
        while (i <= gt) {
            int cmp = c.compare(a[i], v);
            if (cmp < 0)
                exch(a, lt++, i++);
            else if (cmp > 0)
                exch(a, i, gt--);
            else
                i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, c, lo, lt - 1);
        sort(a, c, gt + 1, hi);
    }

    public static void main(String[] args) {
//        String[] a = {"C", "A"};
//        String[] a = {"Q", "U", "I", "C", "K", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        String[] a = {"A", "A", "A", "A", "B", "B", "B", "B", "B", "B", "B", "C", "C", "D", "D", "D", "D", "D", "D"};
        sort(a);
        show(a);
        StdOut.println(isSorted(a));
    }
}
