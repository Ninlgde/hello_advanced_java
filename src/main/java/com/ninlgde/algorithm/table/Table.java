package com.ninlgde.algorithm.table;

/**
 * @author: ninlgde
 * @date: 2/14/21 11:33 PM
 */
public interface Table<K, V> {
    
    void put(K key, V value);

    V get(K key);

    void delete(K key);

    boolean contains(K key);

    boolean isEmpty();

    int size();

    Iterable<K> keys();
}
