package com.ninlgde.patterns.singleton;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:57
 */
public class Singleton6 {
    private static final Singleton6 instance;

    static {
        instance = new Singleton6();
    }

    private Singleton6() {}

    public static Singleton6 getInstance() {
        return instance;
    }
}
