package com.ninlgde.algorithm.sort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

/**
 * @author: ninlgde
 * @date: 2/8/21 2:53 PM
 */
public class QuickQuick extends Quick {

    private static final int M = 40;

    protected static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + M) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    protected static void sort(Object[] a, Comparator c, int lo, int hi) {
        if (hi <= lo + M) {
            Insertion.sort(a, c, lo, hi);
            return;
        }
        int j = partition(a, c, lo, hi);
        sort(a, c, lo, j - 1);
        sort(a, c, j + 1, hi);
    }

    public static void main(String[] args) {
        String[] a = {"Q", "U", "I", "C", "K", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        StdRandom.shuffle(a);
        sort(a);
        show(a);
        StdOut.println(isSorted(a));
    }
}
