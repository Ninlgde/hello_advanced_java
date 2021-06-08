package com.ninlgde.patterns.proxy;

/**
 * @author: ninlgde
 * @date: 2020/4/28 18:13
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("request invoke");
    }

    @Override
    public void response() {
        System.out.println("response invoke");
    }
}
