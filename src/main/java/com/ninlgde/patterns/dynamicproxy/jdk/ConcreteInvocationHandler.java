package com.ninlgde.patterns.dynamicproxy.jdk;

import com.ninlgde.patterns.proxy.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: ninlgde
 * @date: 2020/4/28 18:19
 */
public class ConcreteInvocationHandler implements InvocationHandler {

    private Subject subject;

    public ConcreteInvocationHandler(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("方法拦截增强逻辑-前置处理执行");
        Object result = method.invoke(subject, args);
        System.out.println("方法拦截增强逻辑-后置处理执行");
        return result;
    }
}
