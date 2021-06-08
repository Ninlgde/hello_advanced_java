package com.ninlgde.algorithm.graph.others;

import com.ninlgde.algorithm.graph.IntegerGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/20/21 1:40 AM
 */
public class TwoColor {

    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(IntegerGraph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s])
                dfs(G, s);
        }
    }

    private void dfs(IntegerGraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v])
                isTwoColorable = false;
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }

    public static void main(String[] args) {
        IntegerGraph G = new IntegerGraph(new In(args[0]));
        TwoColor c = new TwoColor(G);
        StdOut.println(c.isBipartite());
    }
}
