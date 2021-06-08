package com.ninlgde.patterns.abstractfactory;

import com.ninlgde.patterns.factory.BenzCar;
import com.ninlgde.patterns.factory.Car;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:45
 */
public class BenzCarAndCatFactory implements AbstractFactory {
    @Override
    public Animal getAnimal() {
        return new Cat();
    }

    @Override
    public Car getCar() {
        return new BenzCar();
    }
}
