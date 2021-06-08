package com.ninlgde.patterns.singleton;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:56
 */
public class Singleton5 {
    private static final Singleton5 instance = new Singleton5();

    private Singleton5() {}

    public static Singleton5 getInstance() {
        return instance;
    }
}
