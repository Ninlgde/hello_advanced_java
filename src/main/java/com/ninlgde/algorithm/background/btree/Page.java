package com.ninlgde.algorithm.background.btree;

/**
 * @author: ninlgde
 * @date: 3/1/21 9:16 PM
 */
public class Page<K> {

    public Page(boolean bottom) {

    }

    public void close() {
    }

    public void add(K key) {
    }

    public void add(Page<K> p) {
    }

    public boolean isExternal() {
        return false;
    }

    public boolean contains(K key) {
        return false;
    }

    public Page<K> next(K key) {
        return null;
    }

    public boolean isFull() {
        return false;
    }

    public Page<K> split() {
        return null;
    }

    public Iterable<K> keys() {
        return null;
    }
}
