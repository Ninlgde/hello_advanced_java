package com.ninlgde.jvm.concurrency;

public class Singleton2 {

    private Singleton2() {
    }

    public static class Singleton2Holder {
        public static Singleton2 instance = new Singleton2();
    }

    public static Singleton2 getInstance() {
        return Singleton2Holder.instance;
    }
}
