package com.ninlgde.guava;


import com.google.common.base.Optional;

/**
 * @author: ninlgde
 * @date: 2021/10/9 14:08
 */
public class OptionalTest {

    public static void main(String[] args) {
        Optional<Integer> o = Optional.of(5);
        System.out.println(o.isPresent());
        System.out.println(o.get());
    }
}
