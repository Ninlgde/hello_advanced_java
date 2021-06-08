package com.ninlgde.algorithm.graph.cc;

import com.ninlgde.algorithm.graph.IntegerDigraph;
import com.ninlgde.algorithm.graph.order.DepthFirstOrder;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ninlgde
 * @date: 2/20/21 6:18 PM
 */
public class KosarajuCC implements SCC<Integer> {

    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuCC(IntegerDigraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(IntegerDigraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    @Override
    public boolean stronglyConnected(Integer v, Integer w) {
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

    public static void main(String[] args) {
        IntegerDigraph G = new IntegerDigraph(new In(args[0]));
        SCC<Integer> cc = new KosarajuCC(G);

        int M = cc.count();
        StdOut.println(M + " components");

        Map<Integer, Bag<Integer>> components = new HashMap<>();
        for (int v = 0; v < G.V(); v++) {
            if (!components.containsKey(cc.id(v)))
                components.put(cc.id(v), new Bag<>());
            components.get(cc.id(v)).add(v);
        }
        for (int key : components.keySet()) {
            for (int v : components.get(key))
                StdOut.print(v + " ");
            StdOut.println();
        }
    }
}
