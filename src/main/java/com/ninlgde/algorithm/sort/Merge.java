package com.ninlgde.algorithm.sort;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/7/21 2:38 PM
 */
public class Merge extends BaseSort {
    protected static Comparable[] aux;

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

//        for (int k = lo; k <= hi; k++) {
//            aux[k] = a[k];
//        }
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);

        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }

    public static void main(String[] args) {
        String[] a = {"E", "E", "G", "M", "R", "A", "C", "E", "R", "T"};
        sort(a);
        show(a);
        StdOut.println(isSorted(a));
    }
}
