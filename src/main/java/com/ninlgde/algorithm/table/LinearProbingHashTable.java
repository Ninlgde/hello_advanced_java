package com.ninlgde.algorithm.table;

import edu.princeton.cs.algs4.Queue;

/**
 * @author: ninlgde
 * @date: 2/18/21 5:46 PM
 */
public class LinearProbingHashTable<K, V> extends AbstractTable<K, V> {
    private int size;
    private int capacity;
    private K[] keys;
    private V[] values;

    public LinearProbingHashTable() {
        this(16);
    }

    public LinearProbingHashTable(int capacity) {
        this.capacity = capacity;
        keys = (K[]) new Object[capacity];
        values = (V[]) new Object[capacity];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void resize(int newCapacity) {
        LinearProbingHashTable<K, V> t = new LinearProbingHashTable<>(newCapacity);
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null)
                t.put(keys[i], values[i]);
        }
        keys = t.keys;
        values = t.values;
        capacity = t.capacity;
    }

    @Override
    public void put(K key, V value) {
        if (size >= capacity / 2)
            resize(capacity * 2);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;

        size++;
    }

    @Override
    public V get(K key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    @Override
    public void delete(K key) {
        if (!contains(key))
            return;
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % capacity;
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % capacity;
        while (keys[i] != null) {
            K keyToRehash = keys[i];
            V valToRehash = values[i];
            keys[i] = null;
            values[i] = null;
            size--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % capacity;
        }
        size--;
        if (size > 0 && size == capacity / 8)
            resize(capacity / 2);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<K> keys() {
        Queue<K> queue = new Queue<>();
        for (int i = 0; i < capacity; i++)
            if (keys[i] != null)
                queue.enqueue(keys[i]);
        return queue;
    }
}
