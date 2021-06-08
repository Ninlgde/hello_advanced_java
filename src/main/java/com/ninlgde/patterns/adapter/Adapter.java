package com.ninlgde.patterns.adapter;

/**
 * @author: ninlgde
 * @date: 2020/4/28 17:03
 */
public class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.request();
    }
}
