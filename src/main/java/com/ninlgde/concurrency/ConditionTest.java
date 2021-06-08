package com.ninlgde.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

/**
 * @author: ninlgde
 * @date: 2020/4/29 17:04
 */
public class ConditionTest {

    public static void main(String[] args) {
        SyncStack ss = new SyncStack();
        Produce pd = new Produce(ss);
        Produce pd2 = new Produce(ss);
        Consume cs = new Consume(ss);
        Consume cs2 = new Consume(ss);
        Thread t1 = new Thread(pd, "produce 1");
        Thread t12 = new Thread(pd2, "produce 2");
        Thread t2 = new Thread(cs, "consume 1");
        Thread t22 = new Thread(cs2, "consume 2");
        t1.start();
        t12.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        t22.start();
    }

    /*
     * 馒头实体类
     */
    static class ManTou {
        private int id;
        private static AtomicInteger incId = new AtomicInteger(0);

        public ManTou(int id) {
            this.id = incId.incrementAndGet();
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "ManTou " + getId();
        }
    }

    /*
     * 馒头框类
     */
    static class SyncStack {
        Lock lock = new ReentrantLock();
        Condition full = lock.newCondition();
        Condition empty = lock.newCondition();
        int index = 0;
        ManTou[] mtArray = new ManTou[6];

        public void push(ManTou mt) {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " push " + mt + " lock");
            try {
                while (index == mtArray.length) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " 满了");
                        full.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                empty.signal();
                mtArray[index] = mt;
                index++;
                System.out.println(Thread.currentThread().getName() + " 生产了 " + mt);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " push " + mt + " unlock");
            }
        }

        public void pop() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " pop" + " lock");
            try {
                while (index == 0) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " 空了");
                        empty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                full.signal();
                index--;
                System.out.println(Thread.currentThread().getName() + " 消费了 " + mtArray[index]);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " pop" + " unlock");
            }
        }
    }

    /*
     * 生产者
     */
    static class Produce implements Runnable {
        SyncStack ss = null;

        public Produce(SyncStack ss) {
            this.ss = ss;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            for (int i = 0; i < 5; i++) {
                ManTou mt = new ManTou(i);
                if (ss != null) {
                    ss.push(mt);
                }
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }

    }

    /*
     * 消费者
     */
    static class Consume implements Runnable {
        SyncStack ss = null;

        public Consume(SyncStack ss) {
            this.ss = ss;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            for (int i = 0; i < 6; i++) {
                if (ss != null) {
                    ss.pop();
                }
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }

    }
}
