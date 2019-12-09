package com.ninlgde.advanced.annotation;

import java.lang.annotation.*;
import java.util.Arrays;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface DocumentA {
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface DocumentB {
}

@DocumentA
class A{ }

class B extends A{ }

@DocumentB
class C{ }

class D extends C{ }

public class DocumentDemo
{
	public static void main(String[] args)
	{
		A instanceA=new B();
		System.out.println("已使用的@Inherited注解:"+ Arrays.toString(instanceA.getClass().getAnnotations()));

		C instanceC = new D();

		System.out.println("没有使用的@Inherited注解:"+Arrays.toString(instanceC.getClass().getAnnotations()));
	}
}
