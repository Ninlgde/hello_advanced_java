package com.ninlgde.algorithm.graph.order;

import com.ninlgde.algorithm.graph.Digraph;
import com.ninlgde.algorithm.graph.SymbolDigraph;
import com.ninlgde.algorithm.graph.others.DirectedCycle;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/20/21 11:45 AM
 */
public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph<Integer> G) {
        DirectedCycle cycle = new DirectedCycle(G);
        if (!cycle.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean idDAG() {
        return order != null;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String separator = args[1];

        SymbolDigraph sg = new SymbolDigraph(filename, separator);

        Topological top = new Topological(sg.G());

        for (int v : top.order()) {
            StdOut.println(sg.name(v));
        }
    }
}
