package com.ninlgde.algorithm.base.lesson3;

import java.util.Iterator;

/**
 * @author: ninlgde
 * @date: 2/5/21 3:12 PM
 */
public class Queue<E> implements Iterable<E> {

    private class Node {
        E item;
        Node prev;
        Node next;
    }

    private Node head = new Node();
    private Node tail = new Node();
    private int size;

    public Queue() {
        head.next = tail;
        tail.prev = head;
    }

    public void enqueue(E item) {
        Node newTail = new Node();
        newTail.item = item;
        newTail.prev = tail;
        tail.next = newTail;
        tail = newTail;
        size++;
    }

    public E dequeue() {
        if (size == 0)
            return null;
        Node n = head.next;
        head = n.next;
        head.prev = null;
        size--;
        return n.item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private class QueueIterator implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }
}
