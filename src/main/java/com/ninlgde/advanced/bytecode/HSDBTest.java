package com.ninlgde.advanced.bytecode;

import java.io.IOException;

public class HSDBTest {
    public static abstract class A {
        public void printMe() {
            System.out.println("I love vim");
        }
        public abstract void sayHello();
    }
    public static class B extends A {
        @Override
        public void sayHello() {
            System.out.println("hello, i am child B");
        }
    }

    public static void main(String[] args) throws IOException {
        A obj = new B();
        System.in.read();
        System.out.println(obj);
    }
}
