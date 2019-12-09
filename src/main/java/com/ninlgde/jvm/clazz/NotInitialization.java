package com.ninlgde.jvm.clazz;

class SuperClass
{
	static
	{
		System.out.println(Thread.currentThread().getName() + "SuperClass init!");
	}

	public static int value = 123;
}

class SubClass extends SuperClass
{
	static
	{
		System.out.println(Thread.currentThread().getName() + "SubClass init!");
	}
}

class ConstClass
{
	static
	{
		System.out.println(Thread.currentThread().getName() + "ConstClass init!");
	}

	public static final String HELLOWORLD = "hello world!";
}

public class NotInitialization
{
	public static void main(String[] args)
	{
		//		System.out.println(SubClass.value);
		//		SuperClass[] sca = new SuperClass[10];
		for (int i = 0; i < 10; i++)
		{
			new Thread(() -> System.out.println(SubClass.value)).start();
		}
	}
}
