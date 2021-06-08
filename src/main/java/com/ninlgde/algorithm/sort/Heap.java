package com.ninlgde.algorithm.sort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author: ninlgde
 * @date: 2/10/21 11:50 AM
 */
public class Heap extends BaseSort {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N/2; k >0; k--)
            sink(a, k-1, N);
        while (N > 0)
        {
            exch(a, 0, --N);
            sink(a, 0, N);
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        while ((k << 1) < N) {
            int j = k << 1;
            if (j < N - 1 && less(a[j], a[j + 1])) j++;
            if (!less(a[k], a[j])) break;
            exch(a, k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
//        String[] a = {"Q", "U", "I", "C", "K", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        String[] a = {"U", "E", "Q", "M", "I", "A", "P", "R", "L", "S", "T", "K", "E", "C", "O", "X"};
//        StdRandom.shuffle(a);
        show(a);
        sort(a);
        show(a);
        StdOut.println(isSorted(a));
    }
}
