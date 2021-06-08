package com.ninlgde.jvm.sugar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GenTest {
    public static void main(String[] args) {
//        ArrayList<Integer> ilist = new ArrayList<>();
//        ArrayList<String> slist = new ArrayList<>();
//        ArrayList list;
//        list = ilist;
//        list = slist;
        Map<String, String> map = new HashMap<>();
        map.put("hello", "你好");
        map.put("how are you?", "吃了没?");
        System.out.println(map.get("how are you?"));
        System.out.println(map.get("hello"));
    }
}
