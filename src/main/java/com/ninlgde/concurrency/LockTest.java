package com.ninlgde.concurrency;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

/**
 * @author: ninlgde
 * @date: 2020/4/29 16:55
 */
public class LockTest {

    public static void main(String[] args) {
        LockObject lo = new LockObject();
        MyThread t1 = new MyThread(lo);
        MyThread t2 = new MyThread(lo);
        Thread ta = new Thread(t1, "A");
        Thread tb = new Thread(t2, "B");

        ta.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tb.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lo.stop = true;
        tb.interrupt();
    }

    static class LockObject {

        public volatile boolean stop = false;

        Lock lock = new ReentrantLock();

        public void LockFuc() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + "请求获取锁");
//            lock.lock(); // 无法被外部线程中断, 该获取还是会获取到
            lock.lockInterruptibly(); // 会被外部线程中断, 继而无法继续后面的操作
            try {
                System.out.println(Thread.currentThread().getName() + "得到了锁");
                while(!stop){
                    ;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁");
            }
        }
    }

    static class MyThread implements Runnable {
        LockObject lo = null;

        public MyThread(LockObject lo) {
            this.lo = lo;
        }

        @Override
        public void run() {
            try {
                lo.LockFuc();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+"被中断");
            }
        }
    }
}
