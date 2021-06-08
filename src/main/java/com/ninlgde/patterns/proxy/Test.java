package com.ninlgde.patterns.proxy;

/**
 * @author: ninlgde
 * @date: 2020/4/28 18:14
 */
public class Test {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        Proxy proxy = new Proxy(subject);
        proxy.request();
        proxy.response();
    }
}
