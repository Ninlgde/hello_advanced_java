package com.ninlgde.algorithm.weightedgraph.sp;

import com.ninlgde.algorithm.weightedgraph.DirectedEdge;
import com.ninlgde.algorithm.weightedgraph.EdgeWeightedDigraph;

/**
 * @author: ninlgde
 * @date: 2/22/21 7:09 PM
 */
public class DijkstraAllPairsSP {

    private DijkstraSP[] all;

    public DijkstraAllPairsSP(EdgeWeightedDigraph G) {
        all = new DijkstraSP[G.V()];
        for (int v = 0; v < G.V(); v++) {
            all[v] = new DijkstraSP(G, v);
        }
    }

    public Iterable<DirectedEdge> path(int s, int t) {
        return all[s].pathTo(t);
    }

    public double dist(int s, int t) {
        return all[s].distTo(t);
    }
}
