package com.ninlgde.algorithm.graph.search;

/**
 * @author: ninlgde
 * @date: 2/19/21 10:50 PM
 */
public interface Search<V> {

    boolean marked(V v);

    int count();
}
