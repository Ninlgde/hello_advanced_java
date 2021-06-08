package com.ninlgde.algorithm.string.search;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author: ninlgde
 * @date: 2/26/21 12:18 AM
 */
public class RabinKarp {

    private String pat;
    private long patHash;
    private int M;
    private long Q;
    private int R = 256;
    private long RM;


    public RabinKarp(String pat) {
        this.pat = pat;
        this.M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        for (int i = 1; i < M; i++)
            RM = (R * RM) % Q;
        patHash = hash(pat, M);
    }

    public boolean check(int i) {
        return true;
    }

    private long hash(String pat, int M) {
        long h = 0;
        for (int j = 0; j < M; j++)
            h = (R * h + pat.charAt(j)) % Q;
        return h;
    }

    private int search(String txt) {
        int N = txt.length();
        long txtHash = hash(txt, M);
        if (patHash == txtHash && check(0)) return 0;
        for (int i = M; i < N; i++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patHash == txtHash)
                if (check(i - M + 1))
                    return i - M + 1;
        }
        return N;
    }

    private long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    public static int search(String pat, String txt) {
        RabinKarp rk = new RabinKarp(pat);
        return rk.search(txt);
    }
}
