package com.ninlgde.algorithm.base.lesson2;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/4/21 11:32 PM
 */
public class Rational implements Comparable<Rational> {

    private final long numerator, denominator;

    public Rational(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public long gcd(long p, long q) {
        if (q == 0)
            return p;
        return gcd(q, p % q);
    }

    public Rational plus(Rational b) {
        long _n = numerator * b.denominator + denominator * b.numerator;
        long _d = denominator * b.denominator;
        long _gcd = gcd(_n, _d);
        return new Rational(_n / _gcd, _d / _gcd);
    }

    public Rational minus(Rational b) {
        long _n = numerator * b.denominator - denominator * b.numerator;
        long _d = denominator * b.denominator;
        long _gcd = gcd(_n, _d);
        return new Rational(_n / _gcd, _d / _gcd);
    }

    public Rational times(Rational b) {
        long _n = numerator * b.numerator;
        long _d = denominator * b.denominator;
        long _gcd = gcd(_n, _d);
        return new Rational(_n / _gcd, _d / _gcd);
    }

    public Rational divides(Rational b) {
        long _n = numerator * b.denominator;
        long _d = denominator * b.numerator;
        long _gcd = gcd(_n, _d);
        return new Rational(_n / _gcd, _d / _gcd);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Rational that = (Rational) obj;
        double times = (double) this.numerator / that.numerator;
        if (that.numerator * times != (double) this.numerator)
            return false;
        if (that.denominator * times != (double) this.denominator)
            return false;
        return true;
    }

    @Override
    public String toString() {
        long _gcd = gcd(numerator, denominator);
        if (_gcd == denominator)
            return (numerator / _gcd) + "";
        if (numerator * denominator < 0) {
            return "-" + Math.abs(numerator) + "/" + Math.abs(denominator);
        }
        return numerator + "/" + denominator;
    }

    @Override
    public int compareTo(Rational o) {
        double _this = (double) numerator / denominator;
        double _that = (double) o.numerator / o.denominator;
        if (_this > _that)
            return 1;
        else if (_this < _that)
            return -1;
        return 0;
    }

    // test client
    public static void main(String[] args) {
        Rational x, y, z;

        // 1/2 + 1/3 = 5/6
        x = new Rational(1, 2);
        y = new Rational(1, 3);
        z = x.plus(y);
        StdOut.println(z);

        // 8/9 + 1/9 = 1
        x = new Rational(8, 9);
        y = new Rational(1, 9);
        z = x.plus(y);
        StdOut.println(z);

        // 1/200000000 + 1/300000000 = 1/120000000
        x = new Rational(1, 200000000);
        y = new Rational(1, 300000000);
        z = x.plus(y);
        StdOut.println(z);

        // 1073741789/20 + 1073741789/30 = 1073741789/12
        x = new Rational(1073741789, 20);
        y = new Rational(1073741789, 30);
        z = x.plus(y);
        StdOut.println(z);

        //  4/17 * 17/4 = 1
        x = new Rational(4, 17);
        y = new Rational(17, 4);
        z = x.times(y);
        StdOut.println(z);

        // 3037141/3247033 * 3037547/3246599 = 841/961
        x = new Rational(3037141, 3247033);
        y = new Rational(3037547, 3246599);
        z = x.times(y);
        StdOut.println(z);

        // 1/6 - -4/-8 = -1/3
        x = new Rational(1, 6);
        y = new Rational(-4, -8);
        z = x.minus(y);
        StdOut.println(z);
    }
}
