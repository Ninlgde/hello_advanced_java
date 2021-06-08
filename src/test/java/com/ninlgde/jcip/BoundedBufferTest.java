package com.ninlgde.jcip;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * BoundedBuffer Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Nov 13, 2019</pre>
 */
public class BoundedBufferTest {
    private static final long LOCKUP_DETECT_TIMEOUT = 1000;
    private static final int CAPACITY = 10000;
    private static final int THRESHOLD = 10000;

    @Test
    public void testIsEmptyWhenConstructed() {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Assert.assertTrue(bb.isEmpty());
        Assert.assertFalse(bb.isFull());
    }

    @Test
    public void testIsFullAfterPuts()
            throws InterruptedException {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        for (int i = 0; i < 10; i++)
            bb.put(i);
        Assert.assertTrue(bb.isFull());
        Assert.assertFalse(bb.isEmpty());
    }

    @Test
    public void testTakeBlocksWhenEmpty() {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Thread taker = new Thread(() -> {
            try {
                int unused = bb.take();
                fail();
            } catch (InterruptedException e) {
            }
        });
        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testLeak()
            throws InterruptedException {
        BoundedBuffer<Big> bb = new BoundedBuffer<>(CAPACITY);
        int heapSize1 = snapshotHeap();
        for (int i = 0; i < CAPACITY; i++)
            bb.put(new Big());
        for (int i = 0; i < CAPACITY; i++)
            bb.take();
        int heapSize2 = snapshotHeap();
        assertTrue(Math.abs(heapSize1 - heapSize2) < THRESHOLD);
    }

    int snapshotHeap() {
        return 0;
    }

    class Big {
        double[] data = new double[100000];
    }

    @Test
    public void testPutTake() {
        PutTakeTest test = new PutTakeTest(10, 10, 100000);
        test.test();
        PutTakeTest.getPool().shutdown();
    }

    @Test
    public void testTimePutTake()
            throws InterruptedException {
        int tpt = 100000;
        for (int cap = 10; cap <= 1000; cap *= 10) {
            System.out.println("Capacity: " + cap);
            for (int pairs = 1; pairs <= 128; pairs *= 2) {
                long startTime = System.nanoTime();
                PutTakeTest test = new PutTakeTest(cap, pairs, tpt);
                System.out.print("Pairs: " + pairs + "\t");
                test.test();
                System.out.print("\t");
                Thread.sleep(1000);
                test.test();
                System.out.println();
                Thread.sleep(1000);
                long endTime = System.nanoTime();
                System.out.println("Total time: " + (endTime - startTime));
            }
        }
        PutTakeTest.getPool().shutdown();
    }

    private static class PutTakeTest {
        private static final ExecutorService pool = Executors.newCachedThreadPool();
        private final AtomicInteger putSum = new AtomicInteger(0);
        private final AtomicInteger takeSum = new AtomicInteger(0);
        private final CyclicBarrier barrier;
        private final BoundedBuffer<Integer> bb;
        private final int nTrials, nPairs;
        private final BarrierTimer timer;

        PutTakeTest(int capacity, int nPairs, int nTrials) {
            this.bb = new BoundedBuffer<>(capacity);
            this.nTrials = nTrials;
            this.nPairs = nPairs;
            timer = new BarrierTimer();
            this.barrier = new CyclicBarrier(nPairs * 2 + 1, timer);
        }

        public static ExecutorService getPool() {
            return pool;
        }

        void test() {
            try {
                timer.clear();
                for (int i = 0; i < nPairs; i++) {
                    pool.execute(new Producer());
                    pool.execute(new Consumer());
                }
                barrier.await();
                barrier.await();
                long nsPerTime = timer.getTime() / (nPairs * (long) nTrials);
                System.out.print("Throughput: " + nsPerTime + "ns/item");
                assertEquals(putSum.get(), takeSum.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        static int xorShift(int y) {
            y ^= (y << 6);
            y ^= (y >>> 21);
            y ^= (y << 7);
            return y;
        }

        class Producer implements Runnable {

            @Override
            public void run() {
                try {
                    int seed = (this.hashCode() ^ (int) System.nanoTime());
                    int sum = 0;
                    barrier.await();
                    for (int i = nTrials; i > 0; i--) {
                        bb.put(seed);
                        sum += seed;
                        seed = xorShift(seed);
                    }
                    putSum.getAndAdd(sum);
                    barrier.await();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        class Consumer implements Runnable {

            @Override
            public void run() {
                try {
                    barrier.await();
                    int sum = 0;
                    for (int i = nTrials; i > 0; i--)
                        sum += bb.take();
                    takeSum.getAndAdd(sum);
                    barrier.await();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public class BarrierTimer implements Runnable {
            private boolean started;
            private long startTime, endTime;

            @Override
            public void run() {
                long t = System.nanoTime();
                if (!started) {
                    started = true;
                    startTime = t;
                } else
                    endTime = t;
            }

            public synchronized void clear() {
                started = false;
            }

            public synchronized long getTime() {
                return endTime - startTime;
            }
        }
    }
} 
