package com.ninlgde.patterns.factory;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:16
 */
public class Client {
    public static void main(String[] args) {
        Factory factory = new BenzCarFactory();

        Car car = factory.getCar();
        car.drive();

        factory = new BMWCarFactory();
        car = factory.getCar();
        car.drive();

        factory = new LandRoverCarFactory();
        car = factory.getCar();
        car.drive();
    }
}
