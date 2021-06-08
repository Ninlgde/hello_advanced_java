package com.ninlgde.patterns.abstractfactory;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:41
 */
public abstract class Animal {
    private String name;
    private String walkStyle;

    public Animal() {
        this.name = "Animal";
        this.walkStyle = "walk on ground";
    }

    public Animal(String name, String walkStyle) {
        this.name = name;
        this.walkStyle = walkStyle;
    }

    public void walk() {
        System.out.println(this.name + " " + this.walkStyle);
    }
}
