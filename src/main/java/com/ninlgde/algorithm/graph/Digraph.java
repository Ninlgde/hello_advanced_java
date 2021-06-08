package com.ninlgde.algorithm.graph;

/**
 * @author: ninlgde
 * @date: 2/20/21 10:19 AM
 */
public interface Digraph<V> extends Graph<V> {
    Digraph<V> reverse();
}
