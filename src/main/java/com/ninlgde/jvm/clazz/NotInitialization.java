package com.ninlgde.jvm.clazz;

class SuperClass {
    static {
        System.out.println(Thread.currentThread().getName() + "SuperClass init!");
    }

    public static int value = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println(Thread.currentThread().getName() + "SubClass init!");
    }
}

class ConstClass {
    static {
        System.out.println(Thread.currentThread().getName() + "ConstClass init!");
    }

    public static final String HELLOWORLD = "hello world!";
    public static final int aaa = 10088;
}

public class NotInitialization {
    public static int bbbb = 10000085;
    public static void main(String[] args) {
//        System.out.println(SubClass.value);
//        SuperClass[] sca = new SuperClass[10];
//        System.out.println(ConstClass.HELLOWORLD);
        System.out.println(ConstClass.aaa);
        System.out.println(bbbb);
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> System.out.println(SubClass.value)).start();
//        }
    }
}
