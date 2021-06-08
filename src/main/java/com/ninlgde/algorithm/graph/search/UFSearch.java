package com.ninlgde.algorithm.graph.search;

import com.ninlgde.algorithm.base.lesson5.WeightedQuickUF;
import com.ninlgde.algorithm.graph.IntegerGraph;
import com.ninlgde.algorithm.graph.search.Search;

/**
 * @author: ninlgde
 * @date: 2/19/21 10:51 PM
 */
public class UFSearch implements Search<Integer> {

    private final WeightedQuickUF uf;
    private final int s;
    private final int count;

    public UFSearch(IntegerGraph G, int s) {
        this.s = s;
        uf = new WeightedQuickUF(G.V());

        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v))
                uf.union(v, w);
        }

        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            if (uf.connected(this.s, v))
                count++;
        }
        this.count = count;
    }

    @Override
    public boolean marked(Integer v) {
        return uf.connected(s, v);
    }

    @Override
    public int count() {
        return count;
    }
}
