package com.ninlgde.patterns.factory;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:10
 */
public class LandRoverCar extends Car {

    public LandRoverCar() {
        super("LandRoverCar");
    }

    public void drive() {
        System.out.println(getName() + " driving on road");
    }
}
