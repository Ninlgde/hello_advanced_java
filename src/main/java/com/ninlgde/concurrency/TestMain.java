package com.ninlgde.concurrency;

public class TestMain {
    public static void main(String[] args)
            throws InterruptedException {
        final Counter c = new Counter();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 100; i1++) {
                    c.add(1);
                    c.add2(1);
                    c.add3(1);
                    c.add4(1);
                }
            }).start();
        }

        Thread.sleep(1000);
        System.out.println(c.count);
        System.out.println(c.count2);
        System.out.println(c.count3);
        System.out.println(c.count4);
    }
}
