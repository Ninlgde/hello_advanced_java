package com.ninlgde.algorithm.sort;

import com.ninlgde.algorithm.base.lesson4.Stopwatch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author: ninlgde
 * @date: 2/7/21 10:43 AM
 */
public class SortCompare {
    public static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion"))
            Insertion.sort(a);
        if (alg.equals("Selection"))
            Selection.sort(a);
        if (alg.equals("Shell"))
            Shell.sort(a);
        if (alg.equals("Merge"))
            Merge.sort(a);
        if (alg.equals("MergeBU"))
            MergeBU.sort(a);
        if (alg.equals("Quick"))
            Quick.sort(a);
        if (alg.equals("QuickQuick"))
            QuickQuick.sort(a);
        if (alg.equals("Heap"))
            Heap.sort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        StdOut.printf("calculating %s sort for %d doubles %d times\n", alg, N, T);
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
            total += time(alg, a);
        }
        StdOut.printf("calculating %s finished, use %.2f seconds\n", alg, total);
        StdOut.printf("------------------------------------------\n");
        return total;
    }

    public static void doublingRatio(String alg1, String alg2, int N, int T) {
        for (int i = N; true; i *= 2) {
            double t1 = timeRandomInput(alg1, i, T);
            double t2 = timeRandomInput(alg2, i, T);

            StdOut.printf("For %d random Doubles\n      %s is", N, alg1);
            StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
            StdOut.printf("==========================================\n");
        }
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];

        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);

        doublingRatio(alg1, alg2, N, T);
    }
}
