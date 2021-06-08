package com.ninlgde.jcip;

import com.ninlgde.jcip.annotations.ThreadSafe;

/**
 * @author: ninlgde
 * @date: 1/19/21 9:37 PM
 */
@ThreadSafe
public class StripedMap<K, V> {
    private static final int N_LOCKS = 16;
    private final Node[] buckets;
    private final Object[] locks;

    public StripedMap(int numBuckets) {
        buckets = new Node[numBuckets];
        locks = new Object[N_LOCKS];
        for (int i = 0; i < N_LOCKS; i++) {
            locks[i] = new Object();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public V get(K key) {
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) {
            for (Node<K, V> m = buckets[hash]; m != null; m = m.next) {
                if (m.key.equals(key))
                    return m.value;
            }
        }
        return null;
    }

    public void put(K key, V value) {
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) {
            Node<K, V> m = buckets[hash];
            if (m == null) {
                buckets[hash] = new Node<>(key, value, null);
            } else {
                m.next = new Node<>(key, value, m.next);
            }
        }
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % N_LOCKS]) {
                buckets[i] = null;
            }
        }
    }

    public void printAll() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % N_LOCKS]) {
                if (buckets[i] != null) {
                    System.out.println("buckets:" + i);
                    for(Node<K, V> m = buckets[i]; m != null; m = m.next) {
                        System.out.println("{key: " + m.key + " value: " + m.value + "}");
                    }
                }
            }
        }
    }

    public static class Node<K, V> {
        public Node<K, V> next;
        public K key;
        public V value;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
