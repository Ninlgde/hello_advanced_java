package com.ninlgde.algorithm.sort;

import java.util.Comparator;

/**
 * @author: ninlgde
 * @date: 2/7/21 10:37 AM
 */
public class Selection extends BaseSort {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
        }
    }

    public static void sort(Object[] a, Comparator c) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(c, a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
        }
    }
}
