package com.ninlgde.effectivejava.lambda_stream;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
 * @author ninlgde
 * @date 2022/10/9 19:19
 */
public class OperationMain {

    public static void main(String[] args) {
        double x = ThreadLocalRandom.current().nextDouble();
        double y = ThreadLocalRandom.current().nextDouble();
        for (Operation op : BaseOperation.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }

//        BigDecimal a = new BigDecimal("sfsdfls").setScale(3, RoundingMode.HALF_UP);
//        System.out.println(a.toString());

//        long t = System.currentTimeMillis();


        String s = "ssssss";
        Object o = s;

        System.out.println(o);

        String value = "j*s";
        String userValue = "fsjlsjfsfsf";

        System.out.println(Pattern.compile(value).matcher(userValue.toString()).find());
    }
}
