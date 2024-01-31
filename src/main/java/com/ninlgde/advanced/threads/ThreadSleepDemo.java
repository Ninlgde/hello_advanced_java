package com.ninlgde.advanced.threads;

/**
 * @author ninlgde
 * @date 2022/4/18 15:31
 */
public class ThreadSleepDemo {
    public static void main(String[] args) {
        new Thread(new MyThread("线程A", 500)).start();
//        new Thread(new MyThread("线程B", 1000)).start();
//        new Thread(new MyThread("线程C", 1500)).start();
    }

    static class MyThread implements Runnable {

        /**
         * 线程名称
         */
        private String name;

        /**
         * 休眠时间
         */
        private int time;

        private MyThread(String name, int time) {
            this.name = name;
            this.time = time;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    // 休眠指定的时间
                    Thread.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            System.out.println(this.name + "休眠" + this.time + "毫秒。");
        }

    }
}
