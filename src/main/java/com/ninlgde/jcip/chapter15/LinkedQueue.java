package com.ninlgde.jcip.chapter15;

import com.ninlgde.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: ninlgde
 * @date: 1/29/21 11:50 PM
 */
@ThreadSafe
public class LinkedQueue<E> {

    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }

    private final Node<E> dummy = new Node<>(null, null);
    private final AtomicReference<Node<E>> head = new AtomicReference<>(dummy);
    private final AtomicReference<Node<E>> tail = new AtomicReference<>(dummy);

    public boolean put(E item) {
        Node<E> newNode = new Node<>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if (curTail == tail.get()) {
                if (tailNext != null) {
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    if (curTail.next.compareAndSet(null, newNode)) {
                        tail.compareAndSet(curTail, newNode);
                        System.out.println("put:" + item);
                        return true;
                    }
                }
            }
        }
    }

    public E take() {
        Node<E> headPrev = head.get(); // dummy
        while (true) {
            Node<E> curHead = headPrev.next.get();
            if (curHead == null)
                return null;
            Node<E> headNext = curHead.next.get();
            if (headPrev.next.compareAndSet(curHead, headNext)) {
                return curHead.item;
            }
        }
    }
}
