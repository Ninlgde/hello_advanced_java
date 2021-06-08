package com.ninlgde.algorithm.graph;

/**
 * @author: ninlgde
 * @date: 2/19/21 10:01 PM
 */
public interface Graph<E> {

    int V();

    int E();

    void addEdge(E v, E w);

    Iterable<E> adj(E v);
}
