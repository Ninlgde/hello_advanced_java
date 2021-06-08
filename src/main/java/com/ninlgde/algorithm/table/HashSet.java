package com.ninlgde.algorithm.table;

import java.util.Iterator;

/**
 * @author: ninlgde
 * @date: 2/19/21 12:24 PM
 */
public class HashSet<K> implements Set<K> {

    private final Table<K, Boolean> table;

    public HashSet() {
        table = new SeparateChainingHashTable<>();
    }

    public HashSet(int capacity) {
        table = new SeparateChainingHashTable<>(capacity);
    }

    @Override
    public void add(K key) {
        table.put(key, true);
    }

    @Override
    public void delete(K key) {
        table.delete(key);
    }

    @Override
    public boolean contains(K key) {
        return table.contains(key);
    }

    @Override
    public boolean isEmpty() {
        return table.isEmpty();
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public Iterator<K> iterator() {
        return table.keys().iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (K key : table.keys()) {
            sb.append(key.toString());
            sb.append(",");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");

        for (String s : set) {
            System.out.println(" " + s);
        }
    }
}
