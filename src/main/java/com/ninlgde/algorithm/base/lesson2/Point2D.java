package com.ninlgde.algorithm.base.lesson2;

import edu.princeton.cs.algs4.StdDraw;

/**
 * @author: ninlgde
 * @date: 2/3/21 12:05 AM
 */
public class Point2D {
    private final double _x, _y;

    public Point2D(double x, double y) {
        this._x = x;
        this._y = y;
    }

    public double x() {
        return _x;
    }

    public double y() {
        return _y;
    }

    public double r() {
        return Math.sqrt(_x * _x + _y * _y);
    }

    public double theta() {
        return Math.atan2(_y, _x);
    }

    public double distTo(Point2D that) {
        double dx = _x - that._x;
        double dy = _y - that._y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void draw() {
        StdDraw.point(_x, _y);
    }
}
