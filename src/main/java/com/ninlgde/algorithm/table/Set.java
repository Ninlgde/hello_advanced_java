package com.ninlgde.algorithm.table;

import java.util.Iterator;

/**
 * @author: ninlgde
 * @date: 2/19/21 12:22 PM
 */
public interface Set<K> extends Iterable<K> {

    void add(K key);

    void delete(K key);

    boolean contains(K key);

    boolean isEmpty();

    int size();

    String toString();

    @Override
    Iterator<K> iterator();
}
