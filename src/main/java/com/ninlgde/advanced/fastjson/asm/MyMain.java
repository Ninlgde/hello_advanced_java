package com.ninlgde.advanced.fastjson.asm;

public class MyMain {
    private int a = 0;
    public int b = 1;

    public int test01(int i) {
        return a + i;
    }

    public void test02() {
    }

    public void foo() {
        System.out.println("step 1");
        int a = 1 / 0;
        System.out.println("step 2");
    }

    public static void main(String[] args) {
        MyMain myMain =  new MyMain();
        System.out.println(myMain.test01(10));
        myMain.foo();
    }
}