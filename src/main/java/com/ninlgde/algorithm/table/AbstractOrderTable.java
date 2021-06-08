package com.ninlgde.algorithm.table;

import edu.princeton.cs.algs4.Queue;

/**
 * @author: ninlgde
 * @date: 2/15/21 12:26 AM
 */
public abstract class AbstractOrderTable<K extends Comparable<K>, V>
        extends AbstractTable<K, V> implements OrderTable<K, V> {

    @Override
    public void deleteMin() {
        delete(min());
    }

    @Override
    public void deleteMax() {
        delete(max());
    }

    @Override
    public int size(K lo, K hi) {
        if (hi.compareTo(lo) < 0)
            return 0;
        else if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

    @Override
    public Iterable<K> keys() {
        if (size() == 0) {
            return new Queue<>();
        }
        return keys(min(), max());
    }
}
