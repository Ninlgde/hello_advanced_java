package com.ninlgde.algorithm.string.search;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/24/21 9:40 PM
 */
public class KMP {

    private String pat;
    private int[][] dfa;

    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 3;
        dfa = new int[R][M];
        dfa[pat.charAt(0)-'A'][0] = 1;
        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][X];
            dfa[pat.charAt(j)-'A'][j] = j + 1;
            X = dfa[pat.charAt(j)-'A'][X];
            StdOut.println(X);
        }
    }

    public int search(String txt) {
        int i, j, N = txt.length(), M = pat.length();
        for (i = 0, j = 0; i < N && j < M; i++)
            j = dfa[txt.charAt(i)][j];
        if (j == M) return i - M;
        else return N;
    }

    public static int search(String pat, String txt) {
        KMP kmp = new KMP(pat);
        return kmp.search(txt);
    }

    public static void main(String[] args) {
        String pat = "ABCABCABABABC";
        KMP kmp = new KMP(pat);
        for (char i = 0; i < 3; i++) {
            for (int j = 0; j < pat.length(); j++) {
                StdOut.print(kmp.dfa[i][j] + " ");
            }
            StdOut.println();
        }
    }
}
