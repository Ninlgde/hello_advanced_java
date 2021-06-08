package com.ninlgde.patterns.factory;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:09
 */
public class BenzCar extends Car {

    public BenzCar() {
        super("BenzCar");
    }

    public void drive() {
        System.out.println(getName() + " driving on road");
    }
}
