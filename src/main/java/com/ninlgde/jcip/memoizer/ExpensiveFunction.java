package com.ninlgde.jcip.memoizer;

import java.math.BigInteger;

/**
 * @author: ninlgde
 * @date: 11/24/20 4:03 PM
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) {
        return new BigInteger(arg);
    }
}
