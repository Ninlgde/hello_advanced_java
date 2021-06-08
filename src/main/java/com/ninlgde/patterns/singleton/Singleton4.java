package com.ninlgde.patterns.singleton;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:52
 */
public class Singleton4 {
    // 如果没有volatile 可能会发生指令重拍导致get到null instance
    private static volatile Singleton4 instance;

    private Singleton4() {}

    public static Singleton4 getInstance() {
        if(instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
