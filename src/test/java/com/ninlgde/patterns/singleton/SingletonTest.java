package com.ninlgde.patterns.singleton;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:54
 */
public class SingletonTest {

    @Test
    public void testLazymanNonThreadSafeSingleon() {
        Runnable myRunable = new Runnable() {
            @Override
            public void run() {
                Singleton1 instance = Singleton1.getInstance();
                System.out.println(instance);
            }
        };

        Thread thread1 = new Thread(myRunable);
        Thread thread2 = new Thread(myRunable);

        thread1.start();
        thread2.start();
    }

    @Test
    public void testLazymanSingleon() {
        Runnable myRunable = new Runnable() {
            @Override
            public void run() {
                Singleton3 instance = Singleton3.getInstance();
                System.out.println(instance);
            }
        };

        Thread thread1 = new Thread(myRunable);
        Thread thread2 = new Thread(myRunable);

        thread1.start();
        thread2.start();
    }

    @Test
    public void testDoubleCheckSingleon() {
        Runnable myRunable = new Runnable() {
            @Override
            public void run() {
                Singleton4 instance = Singleton4.getInstance();
                System.out.println(instance);
            }
        };

        Thread thread1 = new Thread(myRunable);
        Thread thread2 = new Thread(myRunable);

        thread1.start();
        thread2.start();
    }
}