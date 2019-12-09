package com.ninlgde.jvm.sugar;

import java.util.ArrayList;
import java.util.List;

public class GenericTypes {
//	public static void method(List<String> list)
//	{
//		System.out.println("invoke method(List<String> list)");
//	}
//
//	public static void method(List<Integer> list)
//	{
//		System.out.println("invoke method(List<Integer> list)");
//	}
//
//	public static String method(List<String> list)
//	{
//		System.out.println("invoke method(List<String> list)");
//		return "";
//	}

    public static int method(List<Integer> list) {
        System.out.println("invoke method(List<Integer> list)");
        return 1;
    }

    public static void method(int[] list) {
        System.out.println("invoke method(int[] list)");
    }

    public static void method(String[] list) {
        System.out.println("invoke String(int[] list)");
    }

    public static void main(String[] args) {
//		method(new ArrayList<String>());
        method(new ArrayList<Integer>());
        method(new int[]{});
        method(new String[]{});
    }
}
