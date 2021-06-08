package com.ninlgde.algorithm.table;

/**
 * @author: ninlgde
 * @date: 2/15/21 12:15 AM
 */
public interface OrderTable<K extends Comparable<K>, V> extends Table<K, V> {

    K min();

    K max();

    K floor(K key);

    K ceiling(K key);

    int rank(K key);

    K select(int k);

    void deleteMin();

    void deleteMax();

    int size(K lo, K hi);

    Iterable<K> keys(K lo, K hi);
}
