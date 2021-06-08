package com.ninlgde.algorithm.graph.cc;

/**
 * @author: ninlgde
 * @date: 2/20/21 1:05 AM
 */
public interface CC<V> {
    boolean connected(V v, V w);

    int count();

    int id(V v);
}
