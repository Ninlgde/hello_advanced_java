package com.ninlgde.algorithm.background.event;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

/**
 * @author: ninlgde
 * @date: 3/1/21 7:31 PM
 */
public class Particle {

    private static final double INFINITY = Double.POSITIVE_INFINITY;

    private double rx, ry;
    private double vx, vy;
    private int count;
    private final double s;
    private final double mass;
    private final Color color;    // color

    public Particle() {
        rx = StdRandom.uniform(0.0, 1.0);
        ry = StdRandom.uniform(0.0, 1.0);
        vx = StdRandom.uniform(-0.005, 0.005);
        vy = StdRandom.uniform(-0.005, 0.005);
        s = StdRandom.uniform(0.002, 0.005);
        mass = s * 100;
        color = Color.RED;
    }

    public Particle(double rx, double ry, double vx, double vy, double s, double mass, Color color) {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.s = s;
        this.mass = mass;
        this.color = color;
    }

    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, s);
    }

    public void move(double dt) {
        rx += vx * dt;
        ry += vy * dt;
    }

    public int count() {
        return count;
    }

    public double timeToHit(Particle that) {
        if (this == that) return INFINITY;
        double dx = that.rx - this.rx;
        double dy = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        if (dvdr > 0) return INFINITY;
        double dvdv = dvx * dvx + dvy * dvy;
        if (dvdv == 0) return INFINITY;
        double drdr = dx * dx + dy * dy;
        double sigma = this.s + that.s;
        double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
        // if (drdr < sigma*sigma) StdOut.println("overlapping particles");
        if (d < 0) return INFINITY;
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public double timeToHitHorizontalWall() {
        if (vy > 0) return (1.0 - ry - s) / vy;
        else if (vy < 0) return (s - ry) / vy;
        else return INFINITY;
    }

    public double timeToHitVerticalWall() {
        if (vx > 0) return (1.0 - rx - s) / vx;
        else if (vx < 0) return (s - rx) / vx;
        else return INFINITY;
    }

    public void bounceOff(Particle that) {
        double dx = that.rx - this.rx;
        double dy = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;             // dv dot dr
        double dist = this.s + that.s;   // distance between particle centers at collison

        // magnitude of normal force
        double magnitude = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);

        // normal force, and in x and y directions
        double fx = magnitude * dx / dist;
        double fy = magnitude * dy / dist;

        // update velocities according to normal force
        this.vx += fx / this.mass;
        this.vy += fy / this.mass;
        that.vx -= fx / that.mass;
        that.vy -= fy / that.mass;

        // update collision counts
        this.count++;
        that.count++;
    }

    public void bounceOffHorizontalWall() {
        vy = -vy;
        count++;
    }

    public void bounceOffVerticalWall() {
        vx = -vx;
        count++;
    }
}
