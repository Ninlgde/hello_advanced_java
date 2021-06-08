package com.ninlgde.algorithm.base.lesson4;

/**
 * @author: ninlgde
 * @date: 2/5/21 6:26 PM
 */
public class ThreeSum {

    public static int count(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        cnt++;
    return cnt;
    }
}
