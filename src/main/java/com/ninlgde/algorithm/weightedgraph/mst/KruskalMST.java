package com.ninlgde.algorithm.weightedgraph.mst;

import com.ninlgde.algorithm.weightedgraph.Edge;
import com.ninlgde.algorithm.weightedgraph.EdgeWeightedGraph;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/**
 * @author: ninlgde
 * @date: 2/22/21 10:01 AM
 */
public class KruskalMST implements MST {

    private Queue<Edge> mst;
    private double weight;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges())
            pq.insert(e);
        UF uf = new UF(G.V());

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w))
                continue;
            uf.union(v, w);
            mst.enqueue(e);
            weight += e.weight();
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        return weight;
    }
}
