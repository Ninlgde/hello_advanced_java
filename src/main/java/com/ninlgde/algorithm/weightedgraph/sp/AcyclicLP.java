package com.ninlgde.algorithm.weightedgraph.sp;

import com.ninlgde.algorithm.graph.order.Topological;
import com.ninlgde.algorithm.weightedgraph.DirectedEdge;
import com.ninlgde.algorithm.weightedgraph.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Stack;

/**
 * @author: ninlgde
 * @date: 2/22/21 7:50 PM
 */
public class AcyclicLP extends AbstractSP {

    public AcyclicLP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.NEGATIVE_INFINITY;
        }
        distTo[s] = 0.0;

        Topological top = new Topological(G);
        for (int v : top.order()) {
            relax(G, v);
        }
    }

    @Override
    protected void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] < distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }
}
