package com.ninlgde.jvm.vmpractice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {
        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    interface IHi {
        void sayHi(String name);
    }

    static class Hi implements IHi {
        @Override
        public void sayHi(String name) {
            System.out.println("hi " + name);
        }
    }

    static class DynamicProxy implements InvocationHandler {
        Object originalObj;

        Object bind(Object originalObj) {
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), originalObj.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            System.out.println("Welcome");
            return method.invoke(originalObj, args);
        }
    }

    public static void main(String[] args) {
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();
//		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        IHi hi = (IHi) new DynamicProxy().bind(new Hi());
        hi.sayHi("123");
    }
}
