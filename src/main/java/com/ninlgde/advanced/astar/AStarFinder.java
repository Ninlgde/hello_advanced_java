package com.ninlgde.advanced.astar;

import com.ninlgde.advanced.astar.math.Vector2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author ninlgde
 * @date 2022/9/23 12:07
 */
public class AStarFinder implements Runnable {
    private Vector2 goal;
    private Set<Vector2> visitedCache;
    private static Vector2 start;
    private static Vector2 over;
    private static Field2D fieldMap;
    private static AStarFinder astar;
    private boolean flying;
    private boolean flag;
    private Field2D field;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private BinaryHeap<Vector2Node> openNode;
    private BinaryHeap<Vector2Node> closeNode;
    private AStarFinderListener pathFoundListener;

    public AStarFinder() {
        this(false);
    }

    public AStarFinder(boolean flying) {
        this.flying = flying;
    }

    public AStarFinder(Field2D field, int startX, int startY, int endX, int endY, boolean flying, boolean flag, AStarFinderListener callback) {
        this.field = field;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.flying = flying;
        this.flag = flag;
        this.pathFoundListener = callback;
    }

    public AStarFinder(Field2D field, int startX, int startY, int endX, int endY, boolean flying, boolean flag) {
        this(field, startX, startY, endX, endY, flying, flag, null);
    }

    public void update(AStarFinder find) {
        this.field = find.field;
        this.startX = find.startX;
        this.startY = find.startY;
        this.endX = find.endX;
        this.endY = find.endY;
        this.flying = find.flying;
        this.flag = find.flag;
    }

    public boolean equals(Object o) {
        if (o instanceof AStarFinder) {
            return this.pathFoundListener == ((AStarFinder) o).pathFoundListener;
        } else {
            return false;
        }
    }

    public static LinkedList<Vector2> find(int[][] maps, int x1, int y1, int x2, int y2, boolean flag) {
        if (astar == null) {
            astar = new AStarFinder();
        }

        if (start == null) {
            start = new Vector2((float) x1, (float) y1);
        } else {
            start.set((float) x1, (float) y1);
        }

        if (over == null) {
            over = new Vector2((float) x2, (float) y2);
        } else {
            over.set((float) x2, (float) y2);
        }

        return find(maps, start, over, flag);
    }

    public static LinkedList<Vector2> find(Field2D maps, int x1, int y1, int x2, int y2, boolean flag) {
        if (astar == null) {
            astar = new AStarFinder();
        }

        if (start == null) {
            start = new Vector2((float) x1, (float) y1);
        } else {
            start.set((float) x1, (float) y1);
        }

        if (over == null) {
            over = new Vector2((float) x2, (float) y2);
        } else {
            over.set((float) x2, (float) y2);
        }

        return astar.calc(maps, start, over, flag);
    }

    public static LinkedList<Vector2> find(Field2D maps, Vector2 sstart, Vector2 goal, boolean flag) {
        if (astar == null) {
            astar = new AStarFinder();
        }

        start = sstart;
        over = goal;
        return astar.calc(maps, start, over, flag);
    }

    public static LinkedList<Vector2> find(int[][] maps, Vector2 start, Vector2 goal, boolean flag) {
        if (astar == null) {
            astar = new AStarFinder();
        }

        if (fieldMap == null) {
            fieldMap = new Field2D(maps);
        } else {
            fieldMap.setMap(maps);
        }

        return astar.calc(fieldMap, start, goal, flag);
    }

    public LinkedList<Vector2> findPath() {
        if (start == null) {
            start = new Vector2((float) this.startX, (float) this.startY);
        } else {
            start.set((float) this.startX, (float) this.startY);
        }

        if (over == null) {
            over = new Vector2((float) this.endX, (float) this.endY);
        } else {
            over.set((float) this.endX, (float) this.endY);
        }

        return this.calc(this.field, start, over, this.flag);
    }

    private LinkedList<Vector2> calc(Field2D field, Vector2 start, Vector2 goal, boolean flag) {
        if (start.equals(goal)) {
            LinkedList<Vector2> v = new LinkedList<>();
            v.add(start);
            return v;
        } else {
            this.goal = goal;
            if (this.visitedCache == null) {
                this.visitedCache = new HashSet<>();
            } else {
                this.visitedCache.clear();
            }

            if (!field.isHit(goal) && !this.flying) {
                this.goal = this.findTheNearestEnd(field, goal);
            }

            this.visitedCache.add(start);
            if (this.openNode == null) {
                this.openNode = new BinaryHeap<>(16, false);
            } else {
                this.openNode.clear();
            }

            Vector2Node node = new Vector2Node(0.0F);
            node.data = start;
            node.g = 0.0F;
            node.h = (float) field.score(goal, start);
            this.openNode.add(node);
            if (this.closeNode == null) {
                this.closeNode = new BinaryHeap<>(16, true);
            } else {
                this.closeNode.clear();
            }

            this.astar(field, flag);
            return this.findPathFromCloseNode(field);
        }
    }

    private Vector2 findTheNearestEnd(Field2D field, Vector2 end) {
        Vector2 nearestEnd = end;

        do {
            int scoreEnd = field.score(nearestEnd, start);
            Vector2[] list = field.neighbors(nearestEnd, true);

            for (int i = 0; i < 8; ++i) {
                Vector2 next = list[i];
                if (next != null && !this.visitedCache.contains(next)) {
                    this.visitedCache.add(next);
                    int score = field.score(next, start);
                    if (score < scoreEnd) {
                        nearestEnd = next;
                        scoreEnd = score;
                    }
                }
            }
        } while (!field.isHit(nearestEnd));

        this.visitedCache.clear();
        return nearestEnd;
    }

    private void astar(Field2D field, boolean flag) {
        while (!this.openNode.isEmpty()) {
            Vector2Node oNode = this.openNode.pop();
            Vector2 current = oNode.data;
            Vector2Node cNode = new Vector2Node(oNode.g);
            cNode.data = oNode.data;
            cNode.f = oNode.f;
            cNode.h = oNode.h;
            cNode.g = oNode.g;
            this.closeNode.add(cNode);
            if (current.equals(this.goal)) {
                return;
            }

            Vector2[] list = field.neighbors(current, flag);

            for (int i = 0; i < 8; ++i) {
                Vector2 next = list[i];
                if (next != null && !this.visitedCache.contains(next)) {
                    this.visitedCache.add(next);
                    if (field.isHit(next) || this.flying) {
                        float g = oNode.g + 1.0F + (float) (i / 4);
                        float h = (float) field.score(this.goal, next);
                        float f = g + h;
                        Vector2Node node = new Vector2Node(f);
                        node.data = next;
                        node.g = g;
                        node.h = h;
                        node.f = f;
                        this.openNode.add(node);
                    }
                }
            }
        }

    }

    private LinkedList<Vector2> findPathFromCloseNode(Field2D field) {
        LinkedList<Vector2> list = new LinkedList<>();
        Vector2Node ppNode = this.closeNode.pop();
        Vector2Node pNode = ppNode;
        list.add(ppNode.data);
        boolean firstIn = true;

        while (!this.closeNode.isEmpty()) {
            Vector2Node node = this.closeNode.pop();
            if (node.data.equals(start)) {
                list.addFirst(pNode.data);
                list.addFirst(start);
                break;
            }

            if (field.score(pNode.data, node.data) == 1) {
                if (firstIn) {
                    pNode = node;
                    firstIn = false;
                } else if (field.isSNeighbor(node.data, ppNode.data)) {
                    pNode = node;
                } else {
                    list.addFirst(pNode.data);
                    ppNode = pNode;
                    pNode = node;
                }
            }
        }

        return list;
    }

    public int getStartX() {
        return this.startX;
    }

    public int getStartY() {
        return this.startY;
    }

    public int getEndX() {
        return this.endX;
    }

    public int getEndY() {
        return this.endY;
    }

    public boolean isFlying() {
        return this.flying;
    }

    public void setFlying(boolean flying) {
        astar.flying = flying;
    }

    public void run() {
        if (this.pathFoundListener != null) {
            this.pathFoundListener.pathFound(this.findPath());
        }

    }

    public static class Vector2Node extends BinaryHeap.Node {
        Vector2 data;
        float g;
        float h;
        float f;

        public Vector2Node(float value) {
            super(value);
        }

        public String toString() {
            return "x:" + this.data.x + "/y:" + this.data.y;
        }
    }
}
