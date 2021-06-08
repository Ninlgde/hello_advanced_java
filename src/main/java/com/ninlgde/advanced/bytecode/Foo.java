package com.ninlgde.advanced.bytecode;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Foo {
    public static void pp(int i) {
        System.out.println("ppp, " + i);
    }
    public void print(String s) {
        System.out.println("hello, " + s);
    }
    public static void main(String[] args) throws Throwable {
        Foo foo = new Foo();

        MethodType methodType = MethodType.methodType(void.class, String.class);
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(Foo.class, "print", methodType);
        methodHandle.invokeExact(foo, "world");

        MethodType methodType1 = MethodType.methodType(void.class, int.class);
        MethodHandle methodHandle1 = MethodHandles.lookup().findStatic(Foo.class, "pp", methodType1);
        methodHandle1.invokeExact( 10);
    }
}
