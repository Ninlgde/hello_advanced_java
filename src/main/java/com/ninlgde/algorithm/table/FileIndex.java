package com.ninlgde.algorithm.table;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

/**
 * @author: ninlgde
 * @date: 2/19/21 12:16 PM
 */
public class FileIndex {
    public static void main(String[] args) {
        Table<String, HashSet<File>> st = new SeparateChainingHashTable<>();
        for (String name : args) {
            File file = new File(name);
            In in = new In(file);
            while (!in.isEmpty()) {
                String word = in.readString();
                if (!st.contains(word))
                    st.put(word, new HashSet<>());
                st.get(word).add(file);
//                StdOut.println(st.get(word));
                StdOut.println(st.get(word).contains(file));
            }
        }

        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            if (st.contains(query)) {
                StdOut.println(st.get(query).size());
                StdOut.println(st.get(query));
                for (File file : st.get(query)) {
                    StdOut.println(" " + file);
                    StdOut.println(" " + file.getName());
                }
            }
        }
    }
}
