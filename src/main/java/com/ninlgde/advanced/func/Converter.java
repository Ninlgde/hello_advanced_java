package com.ninlgde.advanced.func;

/**
 * @author ninlgde
 * @date 2022/4/15 13:50
 */
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
