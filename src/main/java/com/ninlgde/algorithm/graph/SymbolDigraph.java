package com.ninlgde.algorithm.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ninlgde
 * @date: 2/20/21 1:52 AM
 */
public class SymbolDigraph implements Digraph<String> {
    private Map<String, Integer> st;
    private String[] keys;
    private IntegerDigraph G;

    public SymbolDigraph(int V) {

    }

    public SymbolDigraph(String stream, String sp) {
        st = new HashMap<>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            for (int i = 0; i < a.length; i++) {
                if (!st.containsKey(a[i]))
                    st.put(a[i], st.size());
            }
        }
        keys = new String[st.size()];
        for (String name : st.keySet())
            keys[st.get(name)] = name;

//        StdOut.println(keys.length);

        G = new IntegerDigraph(st.size());
        in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            int v = st.get(a[0]);
//            StdOut.println(v);
            for (int i = 1; i < a.length; i++) {
                G.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String s) {
        return st.containsKey(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public IntegerDigraph G() {
        return G;
    }

    @Override
    public int V() {
        return G.V();
    }

    @Override
    public int E() {
        return G.E();
    }

    @Override
    public void addEdge(String v, String w) {
        G.addEdge(st.get(v), st.get(w));
    }

    @Override
    public Iterable<String> adj(String v) {
        Queue<String> queue = new Queue<>();
        int vidx = st.get(v);
        for (int w : G.adj(vidx)) {
            queue.enqueue(keys[w]);
        }
        return queue;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String delim = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, delim);

        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            for (String w : sg.adj(source))
                StdOut.println("  " + w);
        }
    }

    @Override
    public SymbolDigraph reverse() {
        return null;
    }
}
