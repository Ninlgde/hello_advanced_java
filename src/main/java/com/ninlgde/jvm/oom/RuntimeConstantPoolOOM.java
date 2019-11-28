package com.ninlgde.jvm.oom;

import java.util.ArrayList;
import java.util.List;

public class RuntimeConstantPoolOOM
{
	public static void main(String[] args)
	{
		// 1.7 废弃了永久代
		//		List<String> list = new ArrayList<>();
		//
		//		int i = 0;
		//		while (true)
		//			list.add(String.valueOf(i++).intern());
		// 1.7以上执行
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);

		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
	}
}
