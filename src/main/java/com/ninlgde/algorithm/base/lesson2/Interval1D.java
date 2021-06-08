package com.ninlgde.algorithm.base.lesson2;

/**
 * @author: ninlgde
 * @date: 2/3/21 12:12 AM
 */
public class Interval1D {

    public final double lo, hi;

    public Interval1D(double lo, double hi) {
        this.lo = lo;
        this.hi = hi;
    }

    public double length() {
        return hi - lo;
    }

    public boolean contains(double x) {
        return x >= lo && x <= hi;
    }

    public boolean intersect(Interval1D that) {
        return hi >= that.lo;
    }

    public void draw() {

    }
}
