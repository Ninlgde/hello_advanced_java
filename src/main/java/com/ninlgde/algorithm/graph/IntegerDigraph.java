package com.ninlgde.algorithm.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * @author: ninlgde
 * @date: 2/20/21 10:20 AM
 */
public class IntegerDigraph implements Digraph<Integer> {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public IntegerDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public IntegerDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    @Override
    public int V() {
        return V;
    }

    @Override
    public int E() {
        return E;
    }

    @Override
    public void addEdge(Integer v, Integer w) {
        adj[v].add(w);
        E++;
    }

    @Override
    public Iterable<Integer> adj(Integer v) {
        return adj[v];
    }

    @Override
    public IntegerDigraph reverse() {
        IntegerDigraph R = new IntegerDigraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }
}
