package com.ninlgde.algorithm.graph.others;

import com.ninlgde.algorithm.graph.IntegerGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/20/21 1:35 AM
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(IntegerGraph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s])
                dfs(G, s, s);
        }
    }

    private void dfs(IntegerGraph G, int v, int u) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w, v);
            else if (w != u)
                hasCycle = true;
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        IntegerGraph G = new IntegerGraph(new In(args[0]));
        Cycle c = new Cycle(G);
        StdOut.println(c.hasCycle());
    }
}
