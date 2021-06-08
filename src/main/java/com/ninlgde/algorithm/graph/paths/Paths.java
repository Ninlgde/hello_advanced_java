package com.ninlgde.algorithm.graph.paths;

/**
 * @author: ninlgde
 * @date: 2/19/21 11:39 PM
 */
public interface Paths<V> {

    boolean hasPathTo(V w);

    Iterable<V> pathTo(V w);
}
