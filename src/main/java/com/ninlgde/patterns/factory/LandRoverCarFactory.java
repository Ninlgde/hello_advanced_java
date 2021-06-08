package com.ninlgde.patterns.factory;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:15
 */
public class LandRoverCarFactory implements Factory {
    @Override
    public Car getCar() {
        return new LandRoverCar();
    }
}
