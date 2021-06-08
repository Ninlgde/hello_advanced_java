package com.ninlgde.pearls;

import java.util.LinkedHashMap;

public class LRUCache extends LinkedHashMap {

    private static final long serialVersionUID = 1L;
    protected int maxElements;

    public LRUCache(int maxSize) {
        super(maxSize, 0.75f, true);
        maxElements = maxSize;
    }

    protected boolean removeEldestEntry(java.util.Map.Entry eldest) {
        return size() > maxElements;
    }

    public static void main(String[] args) {
        Object key = new Object();
        LRUCache lruCache = new LRUCache(5);
        lruCache.put(key, key);
        lruCache.put(Integer.valueOf(100), 0);
        System.out.println(lruCache.get(Integer.valueOf(100)));
        System.out.println(lruCache.get(key) == key); // true
        lruCache.put(new Object(), 0);
        System.out.println(lruCache.get(new Object()));
        lruCache.put(new Object(), null);
        lruCache.put(new Object(), null);
        lruCache.put(new Object(), null);
        System.out.println(lruCache.get(key) == key); // true
        lruCache.put(new Object(), null);
        lruCache.put(new Object(), null);
        lruCache.put(new Object(), null);
        lruCache.put(new Object(), null);
        System.out.println(lruCache.get(key) == key); // true
        lruCache.put(new Object(), null);
        lruCache.put(new Object(), null);
        lruCache.put(new Object(), null);
        lruCache.put(new Object(), null);
        lruCache.put(new Object(), null);
        System.out.println(lruCache.get(key) == key); // false
    }
}
