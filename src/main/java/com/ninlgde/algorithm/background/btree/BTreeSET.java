package com.ninlgde.algorithm.background.btree;

/**
 * @author: ninlgde
 * @date: 3/1/21 9:16 PM
 */
public class BTreeSET<K extends Comparable<K>> {

    private Page<K> root = new Page<>(true);

    public BTreeSET(K sentinel) {
        add(sentinel);
    }

    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Page<K> h, K key) {
        if (h.isExternal()) return h.contains(key);
        return contains(h.next(key), key);
    }

    public void add(K key) {
        add(root, key);
        if (root.isFull()) {
            Page<K> lefthalf = root;
            Page<K> righthalf = root.split();
            root = new Page<>(false);
            root.add(lefthalf);
            root.add(righthalf);
        }
    }

    public void add(Page<K> h, K key) {
        if (h.isExternal()) {
            h.add(key);
            return;
        }

        Page<K> next = h.next(key);
        add(next, key);
        if (next.isFull())
            h.add(next.split());
        next.close();
    }
}
