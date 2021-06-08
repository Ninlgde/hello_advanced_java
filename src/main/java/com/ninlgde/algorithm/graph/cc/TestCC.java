package com.ninlgde.algorithm.graph.cc;

import com.ninlgde.algorithm.graph.Graph;
import com.ninlgde.algorithm.graph.IntegerGraph;
import com.ninlgde.algorithm.table.SeparateChainingHashTable;
import com.ninlgde.algorithm.table.Table;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ninlgde
 * @date: 2/20/21 1:14 AM
 */
public class TestCC {
    public static void main(String[] args) {
        IntegerGraph G = new IntegerGraph(new In(args[0]));
        CC<Integer> cc = new DepthFirstCC(G);

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
