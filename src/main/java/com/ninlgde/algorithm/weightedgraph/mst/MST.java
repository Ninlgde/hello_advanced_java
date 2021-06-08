package com.ninlgde.algorithm.weightedgraph.mst;

import com.ninlgde.algorithm.weightedgraph.Edge;

/**
 * @author: ninlgde
 * @date: 2/22/21 1:14 AM
 */
public interface MST {

    Iterable<Edge> edges();

    double weight();
}
