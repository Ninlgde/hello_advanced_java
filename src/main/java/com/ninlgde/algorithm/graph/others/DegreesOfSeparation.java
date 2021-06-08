package com.ninlgde.algorithm.graph.others;

import com.ninlgde.algorithm.graph.Graph;
import com.ninlgde.algorithm.graph.IntegerGraph;
import com.ninlgde.algorithm.graph.SymbolGraph;
import com.ninlgde.algorithm.graph.paths.BreadthFirstPaths;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author: ninlgde
 * @date: 2/20/21 9:58 AM
 */
public class DegreesOfSeparation {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);
        IntegerGraph G = sg.G();

        String source = args[2];

        if (!sg.contains(source)) {
            StdOut.println(source + "not in database.");
            return;
        }

        int s = sg.index(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.index(sink);
                if (bfs.hasPathTo(t)) {
                    for (int v : bfs.pathTo(t)) {
                        StdOut.println("    " + sg.name(v));
                    }
                } else {
                    StdOut.println("Not in database.");
                }
            }
        }
    }
}
