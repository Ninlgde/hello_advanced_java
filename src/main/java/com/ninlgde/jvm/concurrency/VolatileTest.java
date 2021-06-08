package com.ninlgde.jvm.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class VolatileTest {
    public static AtomicLong raceUse = new AtomicLong(0);
    public static volatile int race = 0;

    public static void increase() {
        long start = System.nanoTime();
        race++;
        raceUse.addAndGet(System.nanoTime()-start);
    }

    public static AtomicLong race2Use = new AtomicLong(0);
    public final static AtomicInteger race2 = new AtomicInteger(0);

    public static void csaIncrease() {
        long start = System.nanoTime();
        while (true) {
            int r = race2.get();
            if (race2.compareAndSet(r, r + 1))
                break;
        }
        race2Use.addAndGet(System.nanoTime()-start);
    }

    public static AtomicLong race3Use = new AtomicLong(0);
    public final static AtomicInteger race3 = new AtomicInteger(0);

    public static void atomIncrease() {
        long start = System.nanoTime();
        race3.incrementAndGet();
        race3Use.addAndGet(System.nanoTime()-start);
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args)
            throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                    csaIncrease();
                    atomIncrease();
                }
            });
            threads[i].start();
        }

//		while (Thread.activeCount() > 1)
//			Thread.yield();
        Thread.sleep(3000);

        System.out.println(race);
        System.out.println(raceUse.get() + " nano");
        System.out.println(race2.get());
        System.out.println(race2Use.get() + " nano");
        System.out.println(race3.get());
        System.out.println(race3Use.get() + " nano");
    }
}
