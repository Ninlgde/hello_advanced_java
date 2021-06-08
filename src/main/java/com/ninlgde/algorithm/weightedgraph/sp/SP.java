package com.ninlgde.algorithm.weightedgraph.sp;

import com.ninlgde.algorithm.weightedgraph.DirectedEdge;

/**
 * @author: ninlgde
 * @date: 2/22/21 4:09 PM
 */
public interface SP {

    double distTo(int v);

    boolean hasPathTo(int v);

    Iterable<DirectedEdge> pathTo(int v);
}
