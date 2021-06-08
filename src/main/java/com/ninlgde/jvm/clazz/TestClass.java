package com.ninlgde.jvm.clazz;

import java.lang.annotation.Documented;

public class TestClass {

    private final static float f = 1;

    private final static String hello = "hello";

    private int m;

//    public int inc(int a) {
//        return 0;
//    }

    public int inc() {
//        return m+1;
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }

    @SuppressWarnings("null")
    public static A getA() {
        A a = new A();
        for (int i = (int) f; i < a.max; i += A.delta)
            synchronized (a) {
                a = a.desc();
            }
        return a;
    }


    public static class A {
        public int max = 10;
        public static int delta = 2;

        public synchronized A desc() {
            return this;
        }
    }
}
