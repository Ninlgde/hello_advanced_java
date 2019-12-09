package com.ninlgde.netty.httpfile;

public class AAA {
    private static boolean ready = false;
    private static int number = 0;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                System.out.println("no ready");
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args)
            throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(1);
        number = 42;
        ready = true;
    }
}