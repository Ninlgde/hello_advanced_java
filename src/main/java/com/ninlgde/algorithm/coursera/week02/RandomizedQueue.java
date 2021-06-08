package com.ninlgde.algorithm.coursera.week02;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author: ninlgde
 * @date: 3/8/21 8:49 PM
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private final Deque<Item> in;
    private final Deque<Item> out;

    public RandomizedQueue() {
        in = new Deque<>();
        out = new Deque<>();
    }

    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }

    public int size() {
        return in.size() + out.size();
    }

    private void randomEnqueue(Deque<Item> q, Item item) {
        if (StdRandom.bernoulli())
            q.addFirst(item);
        else
            q.addLast(item);
    }

    private Item randomDequeue(Deque<Item> q) {
        if (StdRandom.bernoulli())
            return q.removeFirst();
        else
            return q.removeLast();
    }

    // add the item
    public void enqueue(Item item) {
        randomEnqueue(in, item);
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("com.ninlgde.algorithm.coursera.week02.RandomizedQueue underflow");
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                Item i = randomDequeue(in);
                randomEnqueue(out, i);
            }
        }
        return randomDequeue(out);
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("com.ninlgde.algorithm.coursera.week02.RandomizedQueue underflow");
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                Item i = randomDequeue(in);
                randomEnqueue(out, i);
            }
        }
        Item i = randomDequeue(out);
        randomEnqueue(in, i);
        return i;
    }

    @Override
    public Iterator<Item> iterator() {
        if (in.size() > out.size()) {
            Item i = randomDequeue(in);
            randomEnqueue(out, i);
            return in.iterator();
        } else {
            Item i = randomDequeue(out);
            randomEnqueue(in, i);
            return out.iterator();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

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
