package com.ninlgde.patterns.factory;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:10
 */
public class BMWCar extends Car {

    public BMWCar() {
        super("BMWCar");
    }

    public void drive() {
        System.out.println(getName() + " driving on road");
    }
}
