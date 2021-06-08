package com.ninlgde.patterns.adapter;

/**
 * @author: ninlgde
 * @date: 2020/4/28 17:05
 */
public class Adaptee implements Target {
    public void request() {
        System.out.println("I'am a old function");
    }
}
