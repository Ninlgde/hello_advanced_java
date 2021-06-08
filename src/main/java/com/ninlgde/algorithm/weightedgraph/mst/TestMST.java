package com.ninlgde.algorithm.weightedgraph.mst;

import com.ninlgde.algorithm.weightedgraph.Edge;
import com.ninlgde.algorithm.weightedgraph.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/22/21 1:14 AM
 */
public class TestMST {
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        MST mst = new PrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
