package com.ninlgde.guava;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import javax.annotation.Nullable;

/**
 * @author: ninlgde
 * @date: 2021/10/9 15:18
 */
public class OrderingTest {

    class Foo {
        @Nullable String sortedBy;
        int notSortedBy;
    }

    public static void main(String[] args) {
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };

        Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
            public String apply(Foo foo) {
                return foo.sortedBy;
            }
        });
    }
}
