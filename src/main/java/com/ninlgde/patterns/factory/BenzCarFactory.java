package com.ninlgde.patterns.factory;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:14
 */
public class BenzCarFactory implements Factory {
    @Override
    public Car getCar() {
        return new BenzCar();
    }
}
