package com.ninlgde.algorithm.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/19/21 10:04 PM
 */
public class IntegerGraph implements Graph<Integer> {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public IntegerGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public IntegerGraph(In in) {
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
        adj[w].add(v);
        E++;
    }

    @Override
    public Iterable<Integer> adj(Integer v) {
        return adj[v];
    }

    public static int degree(IntegerGraph G, int v) {
        int degree = 0;
        for (int w : G.adj(v)) {
            degree++;
        }
        return degree;
    }

    public static int maxDegree(IntegerGraph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++) {
            int degree = degree(G, v);
            if (degree > max)
                max = degree;
        }
        return max;
    }

    public static double avgDegree(IntegerGraph G) {
        return 2.0 * G.E() / G.V();
    }

    public static int numberOfSelfLoops(IntegerGraph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v))
                if (v == w)
                    count++;
        }
        return count / 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(V() + " vertices, " + E() + " edges\n");
        for (int v = 0; v < V(); v++) {
            sb.append(v).append(": ");
            for (int w : adj(v))
                sb.append(w).append(" ");
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Graph G = new IntegerGraph(new In(args[0]));
        StdOut.println(G);
    }
}
