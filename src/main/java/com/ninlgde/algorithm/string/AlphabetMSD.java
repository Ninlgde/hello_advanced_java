package com.ninlgde.algorithm.string;

/**
 * @author: ninlgde
 * @date: 2/23/21 6:27 PM
 */
public class AlphabetMSD {

    private int R;
    private final int M = 15;
    private String[] aux;
    private Alphabet alphabet;

    public AlphabetMSD() {
        this(Alphabet.EXTENDED_ASCII);
    }

    public AlphabetMSD(Alphabet alphabet) {
        this.alphabet = alphabet;
        this.R = alphabet.R();
    }

    private int charAt(String s, int d) {
        if (s.length() > d)
            return alphabet.toIndex(s.charAt(d));
        return -1;
    }

    public void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    private void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo + M) {
            Insertion.sort(a, lo, hi, d);
            return;
        }

        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }

        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }

        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }

        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];

        for (int r = 0; r < R; r++)
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
    }
}
