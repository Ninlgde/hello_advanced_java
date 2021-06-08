package com.ninlgde.algorithm.graph.paths;

import com.ninlgde.algorithm.graph.IntegerGraph;
import edu.princeton.cs.algs4.Stack;

/**
 * @author: ninlgde
 * @date: 2/19/21 11:43 PM
 */
public class DepthFirstPaths implements Paths<Integer> {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(IntegerGraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(IntegerGraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    @Override
    public boolean hasPathTo(Integer w) {
        return marked[w];
    }

    @Override
    public Iterable<Integer> pathTo(Integer w) {
        if (!hasPathTo(w))
            return null;
        Stack<Integer> path = new Stack<>();
        for (int x = w; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}
