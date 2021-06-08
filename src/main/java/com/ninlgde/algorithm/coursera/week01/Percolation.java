package com.ninlgde.algorithm.coursera.week01;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author: ninlgde
 * @date: 3/1/21 1:02 AM
 */
public class Percolation {

    private final WeightedQuickUnionUF uf;
    private int openCount;
    private boolean[][] sites;
    private final int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("invalid arguments");
        }
        this.n = n;
        int m = n * n + 2;
        uf = new WeightedQuickUnionUF(m);
        openCount = 0;
        sites = new boolean[n][n];
    }

    private int getUFId(int row, int col) {
        return row * n + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("invalid arguments");
        }
        // fix row,col 1..n -> 0..n-1
        row--;
        col--;
        if (sites[row][col])
            return;
        openCount++;
        sites[row][col] = true;

        connectUp(row, col);
        connectDown(row, col);
        connectLeft(row, col);
        connectRight(row, col);
    }

    private void connectUp(int row, int col) {
        int self = getUFId(row, col);
        if (row == 0) {
            int top = n * n;
            uf.union(self, top);
            return;
        }
        if (sites[row - 1][col]) {
            int up = getUFId(row - 1, col);
            uf.union(self, up);
        }
    }

    private void connectDown(int row, int col) {
        int self = getUFId(row, col);
        if (row == n - 1) {
            int bottom = n * n + 1;
            uf.union(self, bottom);
            return;
        }
        if (sites[row + 1][col]) {
            int down = getUFId(row + 1, col);
            uf.union(self, down);
        }
    }

    private void connectLeft(int row, int col) {
        int self = getUFId(row, col);
        if (col == 0) {
            return;
        }
        if (sites[row][col - 1]) {
            int left = getUFId(row, col - 1);
            uf.union(self, left);
        }
    }

    private void connectRight(int row, int col) {
        int self = getUFId(row, col);
        if (col == n - 1) {
            return;
        }
        if (sites[row][col + 1]) {
            int right = getUFId(row, col + 1);
            uf.union(self, right);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("invalid arguments");
        }
        // fix row,col 1..n -> 0..n-1
        row--;
        col--;
        return sites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("invalid arguments");
        }
        // fix row,col 1..n -> 0..n-1
        row--;
        col--;
        int top = n * n;
        int self = getUFId(row, col);
        return sites[row][col] && uf.find(self) == uf.find(top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        int top = n * n;
        int bottom = n * n + 1;
        return uf.find(top) == uf.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 1);
        System.out.println(p.percolates());
        p.open(2, 2);
        System.out.println(p.percolates());
        p.open(1, 2);
        System.out.println(p.percolates());
        p.open(3, 3);
        System.out.println(p.percolates());
        p.open(2, 3);
        System.out.println(p.percolates());
        System.out.println(p.isFull(3, 1));
    }
}
