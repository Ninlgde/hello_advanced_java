package com.ninlgde.algorithm.string;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/24/21 3:15 PM
 */
public class TST<V> implements StringST<V> {

    private Node root;

    private class Node {
        char c;
        Node left, mid, right;
        V val;
    }

    @Override
    public void put(String key, V value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, V val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) {
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.mid = put(x.mid, key, val, d + 1);
        } else {
            x.val = val;
        }
        return x;
    }

    @Override
    public V get(String key) {
        Node x = get(root, key, 0);
        if (x == null)
            return null;
        return x.val;
    }

    private Node get(Node x, String key, int d) {
        if (key == "")
            return root;
        if (x == null)
            return null;
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid, key, d + 1);
        } else {
            return x;
        }
    }

    @Override
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null)
            return null;

        char c = key.charAt(d);
        if (c < x.c) {
            x.left = delete(x.left, key, d);
        } else if (c > x.c) {
            x.right = delete(x.right, key, d);
        } else if (d < key.length() - 1) {
            x.mid = delete(x.mid, key, d + 1);
        } else {
            x.val = null;
        }

        if (x.left == null && x.mid == null && x.right == null)
            return null;
        return x;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<String> keys() {
        Queue<String> queue = new Queue<String>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }

    @Override
    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    @Override
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> q = new Queue<>();
        Node x = get(root, prefix, 0);
        if (x == null) return q;
        if (x.val != null) q.enqueue(prefix);
        collect(x.mid, new StringBuilder(prefix), q);
        return q;
    }

    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> queue = new Queue<String>();
        collect(root, new StringBuilder(), 0, pattern, queue);
        return queue;
    }

    private void collect(Node x, StringBuilder prefix, Queue<String> queue) {
        if (x == null) return;
        collect(x.left,  prefix, queue);
        if (x.val != null) queue.enqueue(prefix.toString() + x.c);
        collect(x.mid,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }

    private void collect(Node x, StringBuilder pre, String pat, Queue<String> q) {
        int d = pre.length();
        if (x == null) return;
        if (d == pat.length() && x.val != null) q.enqueue(pre.toString() + x.c);
        if (d == pat.length()) return;

        char next = pat.charAt(d);
        if (x.left != null && (next == '.' || next == x.left.c))
            collect(x.left, pre, pat, q);
        if (x.mid != null && (next == '.' || next == x.mid.c))
            collect(x.mid, pre.append(x.c), pat, q);
        if (x.right != null && (next == '.' || next == x.right.c))
            collect(x.right, pre, pat, q);
    }

    private void collect(Node x, StringBuilder prefix, int i, String pattern, Queue<String> queue) {
        if (x == null) return;
        char c = pattern.charAt(i);
        if (c == '.' || c < x.c) collect(x.left, prefix, i, pattern, queue);
        if (c == '.' || c == x.c) {
            if (i == pattern.length() - 1 && x.val != null) queue.enqueue(prefix.toString() + x.c);
            if (i < pattern.length() - 1) {
                collect(x.mid, prefix.append(x.c), i+1, pattern, queue);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        if (c == '.' || c > x.c) collect(x.right, prefix, i, pattern, queue);
    }

    private int search(Node x, String s, int d, int length) {
        if (x == null) return length;
        if (d == s.length()) return length;
        if (x.val != null)
            length = d + 1;
        char c = s.charAt(d);
        if (c > x.c)
            return search(x.left, s, d, length);
        else if (c < x.c)
            return search(x.right, s, d, length);
        else
            return search(x.mid, s, d + 1, length);
    }

    public static void main(String[] args) {

        String[] a = {"she", "sells", "sea", "shells", "by", "the", "sea", "shore"};
        // build symbol table from standard input
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < a.length; i++) {
            String key = a[i];
            st.put(key, i);
        }

        // print results
        if (st.size() < 100) {
            StdOut.println("keys(\"\"):");
            for (String key : st.keys()) {
                StdOut.println(key + " " + st.get(key));
            }
            StdOut.println();
        }

        StdOut.println("longestPrefixOf(\"shellsort\"):");
        StdOut.println(st.longestPrefixOf("shellsort"));
        StdOut.println();

        StdOut.println("longestPrefixOf(\"quicksort\"):");
        StdOut.println(st.longestPrefixOf("quicksort"));
        StdOut.println();

        StdOut.println("keysWithPrefix(\"shor\"):");
        for (String s : st.keysWithPrefix("shor"))
            StdOut.println(s);
        StdOut.println();

        StdOut.println("keysThatMatch(\".he.l.\"):");
        for (String s : st.keysThatMatch(".he.l."))
            StdOut.println(s);
    }
}
