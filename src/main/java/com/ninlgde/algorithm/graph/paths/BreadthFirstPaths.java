package com.ninlgde.algorithm.graph.paths;

import com.ninlgde.algorithm.graph.IntegerGraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * @author: ninlgde
 * @date: 2/20/21 12:51 AM
 */
public class BreadthFirstPaths implements Paths<Integer> {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(IntegerGraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(IntegerGraph G, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(Integer w) {
        return marked[w];
    }

    @Override
    public Iterable<Integer> pathTo(Integer w) {
        if (!hasPathTo(w))
            return null;
        Stack<Integer> path = new Stack<>();
        for (int x = w; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}
