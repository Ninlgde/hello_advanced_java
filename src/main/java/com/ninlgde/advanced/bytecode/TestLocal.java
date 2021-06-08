package com.ninlgde.advanced.bytecode;

public class TestLocal {
    private int a;
    private static int b = 199;
    static {
        System.out.println("log from static block");
    }
    public TestLocal() {
        System.out.println("log from constructor block");
    }

    {
        System.out.println("log from init block");
    }

    public static void main(String[] args) {
        TestLocal testLocal = new TestLocal();
    }
}
