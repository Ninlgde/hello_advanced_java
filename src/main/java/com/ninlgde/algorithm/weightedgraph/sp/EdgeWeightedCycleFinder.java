package com.ninlgde.algorithm.weightedgraph.sp;

import com.ninlgde.algorithm.graph.Digraph;
import com.ninlgde.algorithm.weightedgraph.DirectedEdge;
import com.ninlgde.algorithm.weightedgraph.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/22/21 11:53 PM
 */
public class EdgeWeightedCycleFinder {

    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private Stack<DirectedEdge> cycle;
    private boolean[] onStack;

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onStack = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s])
                dfs(G, s);
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;

        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();

            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
//                double cycleWeight = 0.0;

                DirectedEdge f = e;
                while (f.from() != w) {
                    cycle.push(f);
                    f = edgeTo[f.from()];
//                    cycleWeight += f.weight();
                }
                cycle.push(f);
//                cycleWeight += f.weight();
//                for (DirectedEdge edge = edgeTo[v]; edge != null; edge = edgeTo[edge.from()]) {
//                    cycle.push(edge);
//                    cycleWeight += edge.weight();
//                }
                StdOut.println(cycle.size());

//                if (cycleWeight >= 0)
//                    cycle = null;
                return;
            }
        }

        onStack[v] = false;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
}
