package com.ninlgde.zookeeper.servicediscovery.demo;

import com.ninlgde.zookeeper.servicediscovery.ServiceRegister;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Server {

    private static CountDownLatch semaphore = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        String host = "localhost";
        int port = new Random().nextInt(10000);

        ServiceRegister.register("/demo", host, port);

        semaphore.await();
    }
}
