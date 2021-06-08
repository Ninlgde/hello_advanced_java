package com.ninlgde.patterns.dynamicproxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: ninlgde
 * @date: 2020/4/28 18:25
 */
public class TargetMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("方法拦截增强逻辑-前置处理执行");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("方法拦截增强逻辑-后置处理执行");
        return result;
    }
}
