package com.ninlgde.jvm.oom;

//import java.lang.reflect.Field;
//import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DirectMemoryOOM {
//	private static final int _1MB = 1024 * 1024;
//
//	public static void main(String[] args)
//		throws Exception
//	{
//		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
//		unsafeField.setAccessible(true);
//		Unsafe unsafe = (Unsafe)unsafeField.get(null);
//		while (true)
//			unsafe.allocateMemory(_1MB);
//	}

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();

        l.add(10);
        l.add(0);
        l.add(10);
        l.add(0);

        l.forEach(System.out::println);

        List<Integer> o = l.stream()
                .filter((a) -> a != 0)
                .collect(Collectors.toList());

        for (Object i : o) {
            System.out.println(i);
        }
    }
}
