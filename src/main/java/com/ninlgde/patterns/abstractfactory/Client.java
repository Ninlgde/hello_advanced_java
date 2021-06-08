package com.ninlgde.patterns.abstractfactory;

import com.ninlgde.patterns.factory.Car;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:46
 */
public class Client {
    public static void main(String[] args) {
        // 实例化一个奔驰车和猫工厂
        AbstractFactory factory = new BenzCarAndCatFactory();
        Car car  = factory.getCar();// 得到奔驰车
        Animal animal = factory.getAnimal();// 得到猫
        car.drive();
        animal.walk();

        // 实例化一个宝马车和鸭工厂
//        factory = new BMWCarAndDuckFactory();
//        car = factory.getCar();
//        animal = factory.getAnimal();
//        car.drive();
//        animal.walk();
//
//        // 实例化一个路虎车和猫工厂
//        factory = new LandRoverCarAndCatFactory();
//        car = factory.getCar();
//        animal = factory.getAnimal();
//        car.drive();
//        animal.walk();
    }
}
