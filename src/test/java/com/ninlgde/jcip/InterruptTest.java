package com.ninlgde.jcip;

import org.junit.Test;

/**
 * @author: ninlgde
 * @date: 1/29/21 2:33 PM
 */
public class InterruptTest {

    @Test
    public void test1() {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread.yield();
            }
        });
        thread.start();
        thread.interrupt();
    }

    @Test
    public void test2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread.yield();

                // 响应中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断，程序退出。");
                    return;
                }
            }
        });
        thread.start();
        thread.interrupt();
        thread.join();
    }

    @Test
    public void test3() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                // 响应中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断，程序退出。");
                    return;
                }

                try {
                    Thread.sleep(3000);
                    System.out.println("ssssss");
                } catch (InterruptedException e) {
                    System.out.println("线程休眠被中断，程序退出。");
                }
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
        thread.join();
    }

    @Test
    public void test4() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                // 响应中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断，程序退出。");
                    return;
                }

                try {
                    Thread.sleep(3000);
                    System.out.println("ssssss");
                } catch (InterruptedException e) {
                    System.out.println("线程休眠被中断，程序退出。");
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
        thread.join();
    }
}
