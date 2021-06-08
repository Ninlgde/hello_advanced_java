package com.ninlgde.algorithm.table;

/**
 * @author: ninlgde
 * @date: 2/15/21 12:27 AM
 */
public abstract class AbstractTable<K, V> implements Table<K, V> {

    @Override
    public void delete(K key) {
        put(key, null);
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
