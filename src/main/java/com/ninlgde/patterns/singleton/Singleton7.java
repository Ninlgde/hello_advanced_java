package com.ninlgde.patterns.singleton;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:57
 */
public class Singleton7 {
    static class Inner {
        private static final Singleton7 instance = new Singleton7();
    }

    private Singleton7() {}

    public static Singleton7 getInstance() {
        return Inner.instance;
    }
}
