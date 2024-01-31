package com.ninlgde.advanced.func;


import com.google.common.base.Supplier;

/**
 * @author ninlgde
 * @date 2022/4/15 14:04
 */
public class SupplierTest {
    public static void main(String[] args) {
        Supplier<String> s = () -> "hello";
        System.out.println(s.get());

        Supplier<Person> p = Person::new;
        System.out.println(p.get());
    }
}
