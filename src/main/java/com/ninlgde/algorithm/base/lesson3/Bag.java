package com.ninlgde.algorithm.base.lesson3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * @author: ninlgde
 * @date: 2/5/21 12:55 AM
 */
public class Bag<E> implements Iterable<E> {

    private E[] list;
    private int cap;
    private int size;

    public Bag() {
        cap = 16;
        list = (E[]) new Object[cap];
        size = 0;
    }

    public void add(E item) {
        list[size++] = item;
        if (size == cap) {
            cap <<= 1;
            E[] newList = (E[]) new Object[cap];
            System.arraycopy(list, 0, newList, 0, size - 1);
            list = newList;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<E> {

        private int index = 0;

        public BagIterator() {
        }

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public E next() {
            return list[index++];
        }
    }

    public static void main(String[] args) {
        Bag<Double> numbers = new Bag<>();

        while (!StdIn.isEmpty())
            numbers.add(StdIn.readDouble());

        int N = numbers.size();

        double sum = 0.0;
        for (double x : numbers) {
            sum += x;
        }

        double mean = sum / N;

        sum = 0.0;
        for (double x : numbers) {
            sum += (x - mean) * (x - mean);
        }
        double std = Math.sqrt(sum / (N - 1));

        StdOut.printf("Mean: %.2f\n", mean);
        StdOut.printf("Std dev: %.2f\n", std);
    }
}
