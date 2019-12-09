package com.ninlgde.advanced.reflect;

import java.lang.reflect.Array;

/**
 * Created by zejian on 2017/5/1.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 */
public class ReflectArray
{

	public static void main(String[] args)
		throws ClassNotFoundException
	{
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		//获取数组类型的Class 即int.class
		Class<?> clazz = array.getClass().getComponentType();
		//创建一个具有指定的组件类型和长度的新数组。
		//第一个参数:数组的类型,第二个参数:数组的长度
		Object newArr = Array.newInstance(clazz, 15);
		//获取原数组的长度
		int co = Array.getLength(array);
		//赋值原数组到新数组
		System.arraycopy(array, 0, newArr, 0, co);
		for (int i : (int[])newArr)
		{
			System.out.print(i + ",");
		}

		//创建了一个长度为10 的字符串数组，
		//接着把索引位置为6 的元素设为"hello world!"，然后再读取索引位置为6 的元素的值
		Class clazz2 = Class.forName("java.lang.String");

		//创建一个长度为10的字符串数组，在Java中数组也可以作为Object对象
		Object array2 = Array.newInstance(clazz2, 10);

		//把字符串数组对象的索引位置为6的元素设置为"hello"
		Array.set(array2, 6, "hello world!");

		//获得字符串数组对象的索引位置为5的元素的值
		String str = (String)Array.get(array2, 6);
		System.out.println();
		System.out.println(str);//hello
	}
}