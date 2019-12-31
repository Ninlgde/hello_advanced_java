package com.ninlgde.jcip.vehicle;

import com.ninlgde.jcip.annotations.Immutable;

@Immutable
public class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
