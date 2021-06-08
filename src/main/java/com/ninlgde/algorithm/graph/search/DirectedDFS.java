package com.ninlgde.algorithm.graph.search;

import com.ninlgde.algorithm.graph.IntegerDigraph;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/20/21 10:27 AM
 */
public class DirectedDFS implements Search<Integer> {

    private boolean[] marked;
    private int count;

    public DirectedDFS(IntegerDigraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedDFS(IntegerDigraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int s : sources) {
            if (!marked[s])
                dfs(G, s);
        }
    }

    private void dfs(IntegerDigraph G, int v) {
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

    public static void main(String[] args) {
        IntegerDigraph G = new IntegerDigraph(new In(args[0]));

        Bag<Integer> sources = new Bag<>();
        for (int i = 1; i < args.length; i++) {
            sources.add(Integer.parseInt(args[i]));
        }

        DirectedDFS reachable = new DirectedDFS(G, sources);

        for (int v = 0; v < G.V(); v++) {
            if (reachable.marked(v))
                StdOut.print(v + " ");
        }
        StdOut.println();
    }
}
