package com.ninlgde.patterns.adapter;

/**
 * @author: ninlgde
 * @date: 2020/4/28 17:11
 */
public class Client {
    public static void main(String[] args) {
        Target target = new Adapter(new Adaptee());
        target.request();
    }
}
