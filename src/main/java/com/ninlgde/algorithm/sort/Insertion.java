package com.ninlgde.algorithm.sort;

import java.util.Comparator;

/**
 * @author: ninlgde
 * @date: 2/7/21 10:41 AM
 */
public class Insertion extends BaseSort {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi + 1; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
        }
    }

    public static void sort(Object[] a, Comparator c) {
        sort(a, c, 0, a.length - 1);
    }

    public static void sort(Object[] a, Comparator c, int lo, int hi) {
        for (int i = lo; i < hi + 1; i++) {
            for (int j = i; j > lo && less(c, a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
        }
    }
}
