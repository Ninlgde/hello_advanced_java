package com.ninlgde.algorithm.graph.search;

import com.ninlgde.algorithm.graph.IntegerGraph;

/**
 * @author: ninlgde
 * @date: 2/19/21 11:10 PM
 */
public class DepthFirstSearch implements Search<Integer> {

    private boolean[] marked;
    private int count;

    public DepthFirstSearch(IntegerGraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(IntegerGraph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }

    @Override
    public boolean marked(Integer v) {
        return marked[v];
    }

    @Override
    public int count() {
        return count;
    }
}
