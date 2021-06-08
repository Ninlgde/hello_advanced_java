package com.ninlgde.algorithm.string;

import com.ninlgde.algorithm.sort.BaseSort;

/**
 * @author: ninlgde
 * @date: 2/23/21 9:44 PM
 */
public class Quick3string extends BaseSort {

    private static final int M = 15;

    private static int charAt(String s, int d) {
        if (d < s.length())
            return s.charAt(d);
        return -1;
    }

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    public static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo + M) {
            Insertion.sort(a, lo, hi, d);
            return;
        }
        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) exch(a, lt++, i++);
            else if (t > v) exch(a, i, gt--);
            else i++;
        }

        // a[lo..lt-1] < v = [lt..gt] < a[gt+1..hi]
        sort(a, lo, lt - 1, d);
        if (v >= 0)
            sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);
    }
}
