package com.ninlgde.advanced.bytecode;

public class TestLambda {
    public static void main(String[] args) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hello, inner class");
//            }
//        };
//        for (int i = 0; i < 10; i++) {
//            Runnable r = () -> {
//                System.out.println("hello, lambda");
//            };
//            r.run();
//        }
//        runnable.run();
//        Runnable r1 = () -> {
//            System.out.println("hello, lambda");
//        };
//        r1.run();
//
//        Runnable r2 = () -> {
//            System.out.println("hello, lambda");
//        };
//        r2.run();
//        int i = 0;
//        i = i++ + ++i;
//        System.out.println("i=" + i);
        int i = 0;
//        i = ++i + i++ + i++ + i++;
        i++;
        System.out.println("i=" + i);
    }
}
