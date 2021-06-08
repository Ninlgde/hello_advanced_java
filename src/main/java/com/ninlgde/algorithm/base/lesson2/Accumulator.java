package com.ninlgde.algorithm.base.lesson2;

/**
 * @author: ninlgde
 * @date: 2/4/21 8:29 PM
 */
public class Accumulator {

    private double total;
    private int N;

    public void addDataValue(double val) {
        N++;
        total += val;
    }

    public double mean() {
        return total / N;
    }

    @Override
    public String toString() {
        return "Mean (" + N + " values): " + String.format("%7.5f", mean());
    }
}
