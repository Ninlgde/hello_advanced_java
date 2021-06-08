package com.ninlgde.algorithm.table;

import java.util.Iterator;

/**
 * @author: ninlgde
 * @date: 2/15/21 1:49 AM
 */
public class BinarySearchTable<K extends Comparable<K>, V> extends AbstractOrderTable<K, V> {

    private K[] keys;
    private V[] values;
    private int capacity;
    private int size;

    public BinarySearchTable(int capacity) {
        keys = (K[]) new Comparable[capacity];
        values = (V[]) new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    private void resize() {
        capacity <<= 1;
        K[] newKeys = (K[]) new Comparable[capacity];
        System.arraycopy(keys, 0, newKeys, 0, size);
        keys = newKeys;
        V[] newValues = (V[]) new Object[capacity];
        System.arraycopy(values, 0, newValues, 0, size);
        values = newValues;
    }

    @Override
    public void put(K key, V value) {
        if (size == capacity) {
            resize();
        }
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
//        for (int j = size; j > i; j--) {
//            keys[j] = keys[j - 1];
//            values[j] = values[j - 1];
//        }
        System.arraycopy(keys, i, keys, i+1, size-i);
        System.arraycopy(values, i, values, i+1, size-i);
        keys[i] = key;
        values[i] = value;
        size++;
    }

    @Override
    public V get(K key) {
        if (!isEmpty()) {
            int i = rank(key);
            if (i < size && keys[i].compareTo(key) == 0)
                return values[i];
        }

        return null;
    }

    @Override
    public void delete(K key) {
        if (!contains(key))
            return;
        int i = rank(key);
//        for (int j = i; j < size - 1; j++) {
//            keys[j] = keys[j + 1];
//            values[j] = values[j + 1];
//        }
        System.arraycopy(keys, i+1, keys, i, size-i-1);
        System.arraycopy(values, i+1, values, i, size-i-1);
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public K min() {
        return keys[0];
    }

    @Override
    public K max() {
        return keys[size - 1];
    }

    @Override
    public K floor(K key) {
        if (contains(key))
            return key;
        int i = rank(key);
        if (i < size)
            return keys[i + 1];
        return null;
    }

    @Override
    public K ceiling(K key) {
        int i = rank(key);
        return keys[i];
    }

    @Override
    public int rank(K key) {
        int lo = 0, hi = size - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0)
                hi = mid - 1;
            else if (cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    @Override
    public K select(int k) {
        return keys[k];
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        return () -> new Iterator<K>() {
            int index = rank(lo);

            @Override
            public boolean hasNext() {
                if (contains(hi)) {
                    return index <= rank(hi);
                }
                return index < rank(hi);
            }

            @Override
            public K next() {
                return keys[index++];
            }
        };
    }

    public static void main(String[] args) {
        String[] a = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        int[] b = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        BinarySearchTable<String, Integer> table = new BinarySearchTable<>(4);
        for (int i = 0; i < a.length; i++) {
            table.put(a[i], b[i]);
        }

        table.delete("E");

        for (String key : table.keys("A", "Q")) {
            System.out.println("key" + key);
        }
    }
}
