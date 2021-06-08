package com.ninlgde.algorithm.string;

import com.ninlgde.algorithm.table.Table;

/**
 * @author: ninlgde
 * @date: 2/24/21 9:49 AM
 */
public interface StringST<V> extends Table<String, V> {

    String longestPrefixOf(String s);

    Iterable<String> keysWithPrefix(String s);

    Iterable<String> keysThatMatch(String s);

    default boolean contains(String key) {
        return get(key) != null;
    }

    default boolean isEmpty() {
        return size() == 0;
    }

}