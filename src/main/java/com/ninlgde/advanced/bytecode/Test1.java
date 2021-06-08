package com.ninlgde.advanced.bytecode;

public class Test1 {
    static public class A {
        static {
            System.out.println("A init");
        }
        public A() {
            System.out.println("A Instance");
        }
    }

    static public class B extends A {
        public static final String HELLOWORD = "hello word";
        static {
            System.out.println("B init");
        }
        public B() {
            System.out.println("B Instance");
        }
    }

    public static void main(String[] args) {
//        A a = new B();
//        A[] a = new B[10];
        System.out.println(B.HELLOWORD);
    }
}
