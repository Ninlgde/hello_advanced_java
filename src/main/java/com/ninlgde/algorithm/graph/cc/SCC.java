package com.ninlgde.algorithm.graph.cc;

/**
 * @author: ninlgde
 * @date: 2/20/21 6:11 PM
 */
public interface SCC<V> {

    boolean stronglyConnected(V v, V w);

    int count();

    int id(V v);
}
