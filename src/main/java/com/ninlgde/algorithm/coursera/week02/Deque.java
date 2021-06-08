package com.ninlgde.algorithm.coursera.week02;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author: ninlgde
 * @date: 3/8/21 8:26 PM
 */
public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first, last;
    private int n;

    private static class Node<Item> {
        Item item;
        Node<Item> next, prev;
    }

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }


    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }


    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("item is null");
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        if (oldFirst == null) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("item is null");
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("com.ninlgde.algorithm.coursera.week02.Deque underflow");
        Item pop = first.item;
        first = first.next;
        if (first != null)
            first.prev = null;
        else
            last = null;
        n--;
        return pop;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("com.ninlgde.algorithm.coursera.week02.Deque underflow");
        Item pop = last.item;
        last = last.prev;
        if (last != null)
            last.next = null;
        else
            first = null;
        n--;
        return pop;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<>();

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
