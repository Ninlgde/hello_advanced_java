package com.ninlgde.algorithm.graph.cc;

import com.ninlgde.algorithm.base.lesson5.WeightedQuickUF;
import com.ninlgde.algorithm.graph.IntegerGraph;

/**
 * @author: ninlgde
 * @date: 2/20/21 1:12 AM
 */
public class UFCC implements CC<Integer> {

    private final WeightedQuickUF uf;

    public UFCC(IntegerGraph G) {
        uf = new WeightedQuickUF(G.V());

        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v))
                uf.union(v, w);
        }
    }

    @Override
    public boolean connected(Integer v, Integer w) {
        return uf.connected(v, w);
    }

    @Override
    public int count() {
        return uf.count();
    }

    @Override
    public int id(Integer v) {
        return uf.find(v);
    }
}
