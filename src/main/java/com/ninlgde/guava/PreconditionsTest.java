package com.ninlgde.guava;

import com.google.common.base.Preconditions;

/**
 * @author: ninlgde
 * @date: 2021/10/9 14:17
 */
public class PreconditionsTest {

    public static void main(String[] args) {
        Preconditions.checkArgument(args.length == 1, "hhhhhh");
    }
}
