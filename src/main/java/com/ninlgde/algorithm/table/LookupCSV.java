package com.ninlgde.algorithm.table;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/19/21 9:51 AM
 */
public class LookupCSV {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int keyField = Integer.parseInt(args[1]);
        int valField = Integer.parseInt(args[2]);
        Table<String, String> table = new SeparateChainingHashTable<>();

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String key = tokens[keyField];
            String val = tokens[valField];
            table.put(key, val);
        }

        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            if (table.contains(query)) {
                StdOut.println(table.get(query));
            }
        }
    }
}
