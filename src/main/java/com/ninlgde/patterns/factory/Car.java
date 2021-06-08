package com.ninlgde.patterns.factory;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:09
 */
public abstract class Car {
    private String name;

    public Car() {
        this.name = "This is a Car";
    }

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void drive();
}
