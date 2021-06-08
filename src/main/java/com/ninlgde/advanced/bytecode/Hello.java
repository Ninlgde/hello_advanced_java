package com.ninlgde.advanced.bytecode;

public class Hello {
    public static final long def = Long.MAX_VALUE;
    public static final long def2 = Long.MAX_VALUE;
    private static final long def3 = Long.MAX_VALUE;

    public void foo() {
        new Thread (()-> {
            System.out.println("hello");
        }).start();
    }

    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}
