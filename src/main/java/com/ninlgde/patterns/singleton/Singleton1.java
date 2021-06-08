package com.ninlgde.patterns.singleton;

import com.ninlgde.jcip.annotations.NotThreadSafe;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:48
 */
@NotThreadSafe
public class Singleton1 {
    private static Singleton1 instance;

    private Singleton1() {}

    public static Singleton1 getInstance() {
        if(instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
