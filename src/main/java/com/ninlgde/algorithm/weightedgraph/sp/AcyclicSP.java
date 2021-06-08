package com.ninlgde.algorithm.weightedgraph.sp;

import com.ninlgde.algorithm.graph.order.Topological;
import com.ninlgde.algorithm.weightedgraph.DirectedEdge;
import com.ninlgde.algorithm.weightedgraph.EdgeWeightedDigraph;

/**
 * @author: ninlgde
 * @date: 2/22/21 7:21 PM
 */
public class AcyclicSP extends AbstractSP {

    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        Topological top = new Topological(G);
        for (int v : top.order()) {
            relax(G, v);
        }
    }
}