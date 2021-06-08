package com.ninlgde.zookeeper.servicediscovery.demo;

import com.ninlgde.zookeeper.servicediscovery.ServiceDiscovery;

import java.util.Properties;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.setProperty("ZOOKEEPER_ADDR", "172.16.4.79:2181");
        ServiceDiscovery.getInstance().init(properties);
        while (true) {
            System.out.println(ServiceDiscovery.getInstance().discover("/demo"));
            Thread.sleep(3000);
        }
    }
}
