package com.ninlgde.algorithm.table;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/18/21 4:39 PM
 */
public class SeparateChainingHashTable<K, V> extends AbstractTable<K, V> {

    private static final int INIT_CAPACITY = 4;

    private int size;
    private int capacity;
    private Table<K, V>[] buckets;

    public SeparateChainingHashTable() {
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashTable(int capacity) {
        this.capacity = capacity;
        buckets = new SequentialSearchTable[capacity];
        for (int i = 0; i < capacity; i++)
            buckets[i] = new SequentialSearchTable<>();
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void resize(int newCapacity) {
        SeparateChainingHashTable<K, V> t = new SeparateChainingHashTable<>(newCapacity);

        for (int i = 0; i < capacity; i++) {
            for (K key : buckets[i].keys()) {
                t.put(key, buckets[i].get(key));
            }
        }
        this.capacity = t.capacity;
        this.size = t.size;
        this.buckets = t.buckets;
    }

    @Override
    public void put(K key, V value) {

        if (size >= 8 * capacity)
            resize(2 * capacity);

        buckets[hash(key)].put(key, value);
        size++;
    }

    @Override
    public V get(K key) {
        return buckets[hash(key)].get(key);
    }

    @Override
    public void delete(K key) {
        int i = hash(key);
        if (buckets[i].contains(key))
            size--;
        buckets[i].delete(key);


        if (capacity > INIT_CAPACITY && size <= 2 * capacity)
            resize(capacity / 2);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<K> keys() {
        Queue<K> queue = new Queue<>();
        for (int i = 0; i < capacity; i++) {
            Table<K, V> t = buckets[i];
//            StdOut.println("t.size" + t.size() + t.keys());
            for (K key : t.keys()) {
                queue.enqueue(key);
            }
        }

        return queue;
    }

    public static void main(String[] args) {
        String[] a = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        int[] b = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        SeparateChainingHashTable<String, Integer> table = new SeparateChainingHashTable<>(4);
        for (int i = 0; i < a.length; i++) {
            table.put(a[i], b[i]);
        }

        table.delete("E");

        for (String key : table.keys()) {
            System.out.println("key" + key);
        }
    }
}
