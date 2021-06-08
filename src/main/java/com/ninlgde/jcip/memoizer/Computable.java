package com.ninlgde.jcip.memoizer;

/**
 * @author: ninlgde
 * @date: 11/24/20 4:02 PM
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
