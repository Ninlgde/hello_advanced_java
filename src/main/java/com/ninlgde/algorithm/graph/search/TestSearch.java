package com.ninlgde.algorithm.graph.search;

import com.ninlgde.algorithm.graph.IntegerGraph;
import com.ninlgde.algorithm.graph.search.DepthFirstSearch;
import com.ninlgde.algorithm.graph.search.Search;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/19/21 11:03 PM
 */
public class TestSearch {
    public static void main(String[] args) {
        IntegerGraph G = new IntegerGraph(new In(args[0]));

        int s = Integer.parseInt(args[1]);

        Search search = new DepthFirstSearch(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                StdOut.print(v + " ");
        }
        StdOut.println();
        StdOut.println(search.count());

        if (search.count() != G.V())
            StdOut.print("NOT ");
        StdOut.println("connected");
    }
}
