package com.ninlgde.algorithm.base.lesson3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * @author: ninlgde
 * @date: 2/5/21 11:27 AM
 */
public class Stack<E> implements Iterable<E> {

    private class Node {
        E item;
        Node next;
    }

    private Node head;
    private int size;

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void push(E item) {
        Node oldHead = head;
        head = new Node();
        head.item = item;
        head.next = oldHead;
        size++;
    }

    public E pop() {
        E item = head.item;
        head = head.next;
        size--;
        return item;
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<E> {

        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E r = current.item;
            current = current.next;
            return r;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(10);
        s.push(20);
        s.push(30);
        for (int x : s)
            StdOut.println(x);
        s.pop();
        for (int x : s)
            StdOut.println(x);
    }
}
