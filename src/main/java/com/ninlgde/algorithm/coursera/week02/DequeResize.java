package com.ninlgde.algorithm.coursera.week02;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author: ninlgde
 * @date: 3/8/21 5:38 PM
 */

public class DequeResize<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 8;

    private int head;
    private int tail;
    private Item[] arr;
    private int n;

    // construct an empty deque
    public DequeResize() {
        tail = head = 0;
        arr = (Item[]) new Object[INIT_CAPACITY];
    }

    // is the deque empty?
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

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("item is null");
        if (isEmpty())
            resize(2 * arr.length);
        if (--head < 0)
            head = arr.length - 1;
        arr[head] = item;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("item is null");
        if (isEmpty())
            resize(2 * arr.length);
        arr[tail++] = item;
        if (tail == arr.length)
            tail = 0;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("com.ninlgde.algorithm.coursera.week02.Deque underflow");
        Item pop = arr[head];
        arr[head] = null;
        if (++head == arr.length)
            head = 0;
        n--;
        if (n > 0 && n == (arr.length / 4))
            resize(arr.length / 2);
        return pop;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("com.ninlgde.algorithm.coursera.week02.Deque underflow");
        if (--tail < 0)
            tail = arr.length - 1;
        Item pop = arr[tail];
        arr[tail] = null;
        n--;
        if (n > 0 && n == (arr.length / 4))
            resize(arr.length / 2);
        return pop;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }


    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < size();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = arr[(i + head) % arr.length];
            i++;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        DequeResize<Integer> dq = new DequeResize<>();
        dq.addFirst(4);
        dq.addLast(5);
        dq.addFirst(3);
        dq.addLast(6);
        dq.addFirst(2);
        dq.addLast(7);
        dq.addFirst(1);
        dq.addLast(8);

        for (int i : dq) {
            StdOut.println(i);
        }

        dq.addLast(9);
        int s = dq.size();
        for (int i = 0; i < s; i++)
            StdOut.println(dq.removeLast());
    }

}