package com.ninlgde.algorithm.weightedgraph.sp;

import com.ninlgde.algorithm.weightedgraph.DirectedEdge;
import com.ninlgde.algorithm.weightedgraph.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/22/21 4:10 PM
 */
public class TestSP {
    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        SP sp = new AcyclicSP(G, s);

        for (int t = 0; t < G.V(); t++) {
            StdOut.print(s + " to " + t);
            StdOut.printf(" (%4.2f):", sp.distTo(t));
            if (sp.hasPathTo(t))
                for (DirectedEdge e : sp.pathTo(t))
                    StdOut.print(e + "  ");
            StdOut.println();
        }
    }
}
