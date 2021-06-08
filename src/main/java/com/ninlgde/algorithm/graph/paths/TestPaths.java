package com.ninlgde.algorithm.graph.paths;

import com.ninlgde.algorithm.graph.IntegerGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/19/21 11:48 PM
 */
public class TestPaths {
    public static void main(String[] args) {
        IntegerGraph G = new IntegerGraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        Paths<Integer> search = new BreadthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            StdOut.print(s + " to " + v + ": ");
            if (search.hasPathTo(v)) {
                for (int x : search.pathTo(v))
                    if (x == s)
                        StdOut.print(x);
                    else
                        StdOut.print("-" + x);
            }
            StdOut.println();
        }
    }
}
