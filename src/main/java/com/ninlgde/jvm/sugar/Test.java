package com.ninlgde.jvm.sugar;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        int sum = 0;
        for (int i : list)
            sum += i;

        System.out.println(sum);
    }
}
