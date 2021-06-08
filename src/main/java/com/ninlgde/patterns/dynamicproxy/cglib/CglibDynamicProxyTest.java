package com.ninlgde.patterns.dynamicproxy.cglib;

import com.ninlgde.patterns.proxy.RealSubject;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author: ninlgde
 * @date: 2020/4/28 18:26
 */
public class CglibDynamicProxyTest {
    public static void main(String[] args) throws InterruptedException {
        Enhancer enhancer = new Enhancer();

        // 设置生成代理类的父类class对象
        enhancer.setSuperclass(RealSubject.class);

        // 设置增强目标类的方法拦截器
        MethodInterceptor methodInterceptor = new TargetMethodInterceptor();
        enhancer.setCallback(methodInterceptor);

        // 生成代理类并实例化
        RealSubject proxy = (RealSubject) enhancer.create();

        // 用代理类调用方法
        proxy.request();
        proxy.response();
        proxy.hashCode();
    }
}
