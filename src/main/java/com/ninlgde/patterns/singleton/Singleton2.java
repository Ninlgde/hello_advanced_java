package com.ninlgde.patterns.singleton;

import com.ninlgde.jcip.annotations.ThreadSafe;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:50
 */
@ThreadSafe
public class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {}

    public static synchronized Singleton2 getInstance() {
        if(instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
