package com.ninlgde.algorithm.base.lesson5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/6/21 11:05 PM
 */
public class UF {
    private int[] id;
    private int count;

    public UF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        return find2(p);
    }

    public void union(int p, int q) {
        union2(p, q);
    }

    public int find1(int p) {
        return id[p];
    }

    /**
     * 23:19:41 → time ./java-algs4.sh chapter01/lesson5/UF < data/largeUF.txt                                                                                                                                                                                        [488402f]
     * 6components
     * ./java-algs4.sh chapter01/lesson5/UF < data/largeUF.txt  809.85s user 1.45s system 99% cpu 13:31.32 total
     *
     * @param p
     * @param q
     */
    public void union1(int p, int q) {
        int oldQ = id[q];
        int pId = id[p];
        if (oldQ == pId) return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == oldQ)
                id[i] = pId;
        }
        count--;
    }

    public int find2(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    public void union2(int p, int q) {
        int pRoot = find2(p);
        int qRoot = find2(q);

        if (pRoot == qRoot) return;

        id[pRoot] = qRoot;

        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf = new UF(N);
        int cnt = 0;
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
//            StdOut.println(p + " " + q);
            cnt++;
            if (cnt % 20000 == 0)
                StdOut.println(cnt);
        }
        StdOut.println(uf.count() + " components");
    }
}
