package com.ninlgde.algorithm.table;

import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

/**
 * @author: ninlgde
 * @date: 2/15/21 12:41 AM
 */
public class SequentialSearchTable<K, V> extends AbstractTable<K, V> {

    private Node first;
    private int size;

    public SequentialSearchTable() {
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = value;
                return;
            }
        }
        first = new Node(key, value, first);
        size++;
    }

    @Override
    public V get(K key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    @Override
    public void delete(K key) {
        Node prev = first;
        for (Node x = first; x != null; prev = x, x = x.next) {
            if (key.equals(x.key)) {
                if (prev != first) {
                    prev.next = x.next;
                } else {
                    first = x.next;
                }
                size--;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<K> keys() {
        if (size() == 0) {
            return new Queue<>();
        }
        return new TableIterable();
    }

    private class TableIterable implements Iterable<K> {

        @Override
        public Iterator<K> iterator() {
            return new Iterator<K>() {

                private Node current = first;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public K next() {
                    Node result = current;
                    current = current.next;
                    return result.key;
                }
            };
        }
    }

    private class Node {
        K key;
        V val;
        Node next;

        public Node(K key, V val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
}
