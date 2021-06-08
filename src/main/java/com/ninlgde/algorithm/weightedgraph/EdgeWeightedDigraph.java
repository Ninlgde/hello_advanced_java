package com.ninlgde.algorithm.weightedgraph;

import com.ninlgde.algorithm.graph.Digraph;
import com.ninlgde.algorithm.graph.IntegerDigraph;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/**
 * @author: ninlgde
 * @date: 2/22/21 3:12 PM
 */
public class EdgeWeightedDigraph implements Digraph<Integer> {
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            DirectedEdge edge = new DirectedEdge(v, w, weight);
            addEdge(edge);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    @Override
    public void addEdge(Integer v, Integer w) {
        DirectedEdge edge = new DirectedEdge(v, w, 0.0);
        addEdge(edge);
    }

    @Override
    public Iterable<Integer> adj(Integer v) {
        Queue<Integer> queue = new Queue<>();
        for (DirectedEdge e : adj[v]) {
            queue.enqueue(e.to());
        }
        return queue;
    }

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> b = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj[v]) {
                b.add(e);
            }
        }
        return b;
    }

    @Override
    public EdgeWeightedDigraph reverse() {
        EdgeWeightedDigraph R = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) {
                DirectedEdge re = new DirectedEdge(e.to(), e.from(), e.weight());
                R.addEdge(re);
            }
        }
        return R;
    }
}
