package com.ninlgde.algorithm.base.lesson2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/3/21 12:20 AM
 */
public class Interval2D {
    private final Interval1D _x, _y;

    public Interval2D(Interval1D x, Interval1D y) {
        _x = x;
        _y = y;
    }

    public double area() {
        return _x.length() * _y.length();
    }

    public boolean contains(Point2D p) {
        return _x.contains(p.x()) && _y.contains(p.y());
    }

    public boolean intersect(Interval2D that) {
        return _x.intersect(that._x) && _y.intersect(that._y);
    }

    public void draw() {
        double hw = _x.length() / 2;
        double hh = _y.length() / 2;
        StdDraw.rectangle(_x.lo + hw, _y.lo + hh, hw, hh);
    }

    public static void main(String[] args) {
        double xlo = Double.parseDouble(args[0]);
        double xhi = Double.parseDouble(args[1]);
        double ylo = Double.parseDouble(args[2]);
        double yhi = Double.parseDouble(args[3]);
        int T = Integer.parseInt(args[4]);

        Interval1D xinterval = new Interval1D(xlo, xhi);
        Interval1D yinterval = new Interval1D(ylo, yhi);
        Interval2D box = new Interval2D(xinterval, yinterval);
        box.draw();

        Counter c = new Counter("hits");
        for (int t= 0; t < T; t++) {
            double x = Math.random();
            double y = Math.random();
            Point2D p = new Point2D(x, y);
            if (box.contains(p))
                c.increment();
            else
                p.draw();
        }

        StdOut.println(c);
        StdOut.println(box.area());
    }
}
