package com.ninlgde.patterns.singleton;

import com.ninlgde.jcip.annotations.NotThreadSafe;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:51
 */
@NotThreadSafe
public class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {}

    public static Singleton3 getInstance() {
        if(instance == null) {
            synchronized (Singleton3.class) {
                instance = new Singleton3();
            }
        }
        return instance;
    }
}
