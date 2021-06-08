package com.ninlgde.algorithm.string;

import com.ninlgde.algorithm.sort.BaseSort;

/**
 * @author: ninlgde
 * @date: 2/23/21 9:05 PM
 */
public class Insertion extends BaseSort {

    public static void sort(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1], d); j--)
                exch(a, j, j - 1);
        }
    }

    private static boolean less(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

}
