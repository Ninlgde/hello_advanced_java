package com.ninlgde.advanced.astar;

import com.ninlgde.advanced.astar.math.Vector2;

import java.util.LinkedList;

/**
 * @author ninlgde
 * @date 2022/9/23 12:10
 */
public interface AStarFinderListener {
    void pathFound(LinkedList<Vector2> var1);
}
