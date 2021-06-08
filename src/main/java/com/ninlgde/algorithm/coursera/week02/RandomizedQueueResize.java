package com.ninlgde.algorithm.coursera.week02;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author: ninlgde
 * @date: 3/8/21 6:31 PM
 */
public class RandomizedQueueResize<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 8;

    private int head;
    private int tail;
    private Item[] arr;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueueResize() {
        tail = head = 0;
        arr = (Item[]) new Object[INIT_CAPACITY];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    private void resize(int newCapacity) {
//        StdOut.println("resize" + newCapacity + " " + n);
        Item[] copy = (Item[]) new Object[newCapacity];
        for (int i = 0; i < n; i++) {
            copy[i] = arr[(head + i) % arr.length];
        }
        head = 0;
        tail = n;
        arr = copy;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item is null");
        if (isEmpty())
            resize(2 * arr.length);
        arr[tail++] = item;
        if (tail == arr.length)
            tail = 0;
        n++;
    }

    private int randomIndex(int start) {
        int r = StdRandom.uniform(start, n);
//        StdOut.println("randomIndex " + r);
        return (head + r) % arr.length;
    }

    private void swap(int a, int b) {
        Item t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("com.ninlgde.algorithm.coursera.week02.RandomizedQueue underflow");
        int ridx = randomIndex(0);
        swap(ridx, head);
        Item pop = arr[head];
        arr[head] = null;
        if (++head == arr.length)
            head = 0;
        n--;
        if (n > 0 && n == (arr.length / 4))
            resize(arr.length / 2);
        return pop;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("com.ninlgde.algorithm.coursera.week02.RandomizedQueue underflow");
        int ridx = randomIndex(0);
        return arr[ridx];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < size();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int ridx = randomIndex(i);
//            StdOut.println("next " + ridx);
            swap(ridx, i);
            Item item = arr[(i + head) % arr.length];
            i++;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueueResize<Integer> rq = new RandomizedQueueResize<>();
        for (int i = 0; i < 8; i++)
            rq.enqueue(i);
        for (int i : rq) {
            StdOut.println("iter " + i);
        }

        for (int i = 0; i < 8; i++)
            StdOut.println("sample " + rq.sample());

        for (int i = 0; i < 8; i++)
            StdOut.println("dequeue " + rq.dequeue());
    }

}
