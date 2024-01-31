package com.ninlgde.advanced.astar;

import com.ninlgde.advanced.astar.math.Vector2;
import com.ninlgde.advanced.astar.utils.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ninlgde
 * @date 2022/9/23 11:54
 */
public class Field2D implements Config, Cloneable {
    private static Vector2 vector2;
    private static final Map<Vector2, Integer> directions = new HashMap<>(9);
    private static final Map<Integer, Vector2> directionValues = new HashMap<>(9);
    private Vector2[] result;
    private int[][] data;
    private int tileWidth;
    private int tileHeight;
    private int width;
    private int height;

    static {
        directions.put(new Vector2(0.0F, 0.0F), -1);
        directions.put(new Vector2(1.0F, -1.0F), 3);
        directions.put(new Vector2(-1.0F, -1.0F), 0);
        directions.put(new Vector2(1.0F, 1.0F), 1);
        directions.put(new Vector2(-1.0F, 1.0F), 2);
        directions.put(new Vector2(0.0F, -1.0F), 7);
        directions.put(new Vector2(-1.0F, 0.0F), 4);
        directions.put(new Vector2(1.0F, 0.0F), 5);
        directions.put(new Vector2(0.0F, 1.0F), 6);
        directionValues.put(-1, new Vector2(0.0F, 0.0F));
        directionValues.put(3, new Vector2(1.0F, -1.0F));
        directionValues.put(0, new Vector2(-1.0F, -1.0F));
        directionValues.put(1, new Vector2(1.0F, 1.0F));
        directionValues.put(2, new Vector2(-1.0F, 1.0F));
        directionValues.put(7, new Vector2(0.0F, -1.0F));
        directionValues.put(4, new Vector2(-1.0F, 0.0F));
        directionValues.put(5, new Vector2(1.0F, 0.0F));
        directionValues.put(6, new Vector2(0.0F, 1.0F));
    }

    public Field2D(int[][] data) {
        this(data, 0, 0);
    }

    public Field2D(int[][] data, int w, int h) {
        this.set(data, w, h);
    }

    public void set(int[][] data, int w, int h) {
        this.setMap(data);
        this.setTileWidth(w);
        this.setTileHeight(h);
        this.width = data[0].length;
        this.height = data.length;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int pixelsToTilesWidth(int x) {
        return x / this.tileWidth;
    }

    public int pixelsToTilesHeight(int y) {
        return y / this.tileHeight;
    }

    public int tilesToWidthPixels(int tiles) {
        return tiles * this.tileWidth + 16;
    }

    public int tilesToHeightPixels(int tiles) {
        return tiles * this.tileHeight + 16;
    }

    public int getTileHeight() {
        return this.tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public int getTileWidth() {
        return this.tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getType(int x, int y) {
        try {
            return this.data[x][y];
        } catch (Exception var4) {
            return -1;
        }
    }

    public void setType(int x, int y, int tile) {
        try {
            this.data[x][y] = tile;
        } catch (Exception var5) {
        }

    }

    public int[][] getMap() {
        return this.data;
    }

    public void setMap(int[][] data) {
        this.data = data;
    }

    public boolean isHit(Vector2 point) {
        return this.get(this.data, point) != -1;
    }

    public boolean isHit(int px, int py) {
        return this.get(this.data, px, py) != -1;
    }

    public static int getDirection(float x, float y) {
        if (vector2 == null) {
            vector2 = new Vector2(x, y);
        } else {
            vector2.set(x, y);
        }

        return (Integer) directions.get(vector2);
    }

    public static int getDirectionBy4i(int startX, int startY, int endX, int endY) {
        if (vector2 == null) {
            vector2 = new Vector2();
        }

        float dx = 0.0F;
        float dy = 0.0F;
        if (startX - endX > 10) {
            dx = -1.0F;
        } else if (startX - endX < -10) {
            dx = 1.0F;
        } else {
            dx = 0.0F;
        }

        if (startY - endY > 0) {
            dy = 1.0F;
        } else if (startY - endY < 0) {
            dy = -1.0F;
        } else {
            dy = 0.0F;
        }

        vector2.set(dx, dy);
        return (Integer) directions.get(vector2);
    }

    public static Vector2 getDirection(int type) {
        return (Vector2) directionValues.get(type);
    }

    private static void insertArrays(int[][] arrays, int index, int px, int py) {
        arrays[index][0] = px;
        arrays[index][1] = py;
    }

    public int[][] neighbors(int px, int py, boolean flag) {
        int[][] pos = new int[8][2];
        insertArrays(pos, 0, px, py - 1);
        insertArrays(pos, 0, px + 1, py);
        insertArrays(pos, 0, px, py + 1);
        insertArrays(pos, 0, px - 1, py);
        if (flag) {
            insertArrays(pos, 0, px - 1, py - 1);
            insertArrays(pos, 0, px + 1, py - 1);
            insertArrays(pos, 0, px + 1, py + 1);
            insertArrays(pos, 0, px - 1, py + 1);
        }

        return pos;
    }

    public boolean isSNeighbor(Vector2 v1, Vector2 v2) {
        return this.score(v1, v2) == 2 && v1.x != v2.x && v1.y != v2.y;
    }

    public Vector2[] neighbors(Vector2 pos, boolean flag) {
        int x;
        if (this.result == null) {
            this.result = new Vector2[8];
        } else {
            for (x = 0; x < 8; ++x) {
                this.result[x] = null;
            }
        }

        x = (int) pos.x;
        int y = (int) pos.y;
        this.result[0] = new Vector2((float) x, (float) (y - 1));
        this.result[1] = new Vector2((float) (x + 1), (float) y);
        this.result[2] = new Vector2((float) x, (float) (y + 1));
        this.result[3] = new Vector2((float) (x - 1), (float) y);
        if (flag) {
            this.result[4] = new Vector2((float) (x - 1), (float) (y - 1));
            this.result[5] = new Vector2((float) (x + 1), (float) (y - 1));
            this.result[6] = new Vector2((float) (x + 1), (float) (y + 1));
            this.result[7] = new Vector2((float) (x - 1), (float) (y + 1));
        }

        return this.result;
    }

    public int score(Vector2 goal, Vector2 point) {
        return (int) (Math.abs(point.x - goal.x) + Math.abs(point.y - goal.y));
    }

    public int score(int x, int y, int px, int py) {
        return Math.abs(px - x) + Math.abs(py - y);
    }

    private int get(int[][] data, int px, int py) {
        try {
            return px < this.width && py < this.height ? data[py][px] : -1;
        } catch (Exception var5) {
            return -1;
        }
    }

    private int get(int[][] data, Vector2 point) {
        try {
            return point.x < (float) this.width && point.y < (float) this.height ? data[(int) point.y][(int) point.x] : -1;
        } catch (Exception var4) {
            return -1;
        }
    }

    @Override
    public Field2D clone() {
        try {
            Field2D clone = (Field2D) super.clone();
            clone.data = CollectionUtils.copyOf(this.data);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
