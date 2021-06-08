package com.ninlgde.jcip.chapter15;

import com.ninlgde.jcip.BoundedBuffer;
import com.ninlgde.jcip.BoundedBufferTest;
import org.junit.Test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

/**
 * @author: ninlgde
 * @date: 1/30/21 12:12 AM
 */
public class LinkedQueueTest {

    @Test
    public void putAndTake() {
        LinkedQueue<Integer> lq = new LinkedQueue<>();
        lq.put(10);
        lq.put(20);
        lq.put(30);

        Integer head;
        do {
            head = lq.take();
            System.out.println(head);
        } while (head != null);
    }

    @Test
    public void putAndTakeConcurrent() throws InterruptedException {
        LinkedQueue<Integer> lq = new LinkedQueue<>();
        AtomicInteger c = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 10; i1++) {
                    lq.put(c.getAndIncrement());
                }
            }).start();
        }

        Thread.sleep(1000);

        System.out.println();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Integer head;
                do {
                    head = lq.take();
                    if (head != null)
                        System.out.println(head);
                } while (head != null);
            }).start();
        }
    }
}