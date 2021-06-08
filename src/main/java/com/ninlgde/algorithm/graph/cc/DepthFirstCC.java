package com.ninlgde.algorithm.graph.cc;

import com.ninlgde.algorithm.graph.IntegerGraph;

/**
 * @author: ninlgde
 * @date: 2/20/21 1:06 AM
 */
public class DepthFirstCC implements CC<Integer> {

    private boolean[] marked;
    private int[] id;
    private int count;

    public DepthFirstCC(IntegerGraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(IntegerGraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
    }

    @Override
    public boolean connected(Integer v, Integer w) {
        return id[v] == id[w];
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int id(Integer v) {
        return id[v];
    }
}
