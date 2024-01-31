package com.ninlgde.advanced.func;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author ninlgde
 * @date 2022/4/15 13:34
 */
public class FunctionTest {
    public static void main(String[] args) {
        Something something = new Something();
        Converter<String, String> startsWith = something::startsWith;

        System.out.println(startsWith.convert("ninlgde"));

        Converter<Integer, String> hhh = Something::hhh;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        Map<Integer, String> map2 = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            map2.put(entry.getKey(), hhh.convert(entry.getValue()));
        }

        System.out.println(map);
        System.out.println(map2);

        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        String a = backToString.apply("123");
    }

    private static class Something {
        String startsWith(String prefix) {
            return String.valueOf(prefix.charAt(0));
        }

        static String hhh(Integer i) {
            return Integer.toString(i * 100);
        }
    }
}
