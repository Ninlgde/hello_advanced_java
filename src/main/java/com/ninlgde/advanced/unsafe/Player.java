package com.ninlgde.advanced.unsafe;

/**
 * @author: ninlgde
 * @date: 2020/4/29 02:25
 */
public class Player {
    private int age;
    private String name;

    private Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
