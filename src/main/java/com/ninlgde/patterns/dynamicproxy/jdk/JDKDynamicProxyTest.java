package com.ninlgde.patterns.dynamicproxy.jdk;

import com.ninlgde.patterns.proxy.RealSubject;
import com.ninlgde.patterns.proxy.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author: ninlgde
 * @date: 2020/4/28 18:20
 */
public class JDKDynamicProxyTest {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        InvocationHandler handler = new ConcreteInvocationHandler(subject);
        Subject proxy = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                RealSubject.class.getInterfaces(), handler);
        proxy.request();
        proxy.response();
        proxy.hashCode();
    }
}
