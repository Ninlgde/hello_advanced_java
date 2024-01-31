package com.ninlgde.advanced.astar.math;

import com.ninlgde.advanced.astar.utils.NumberUtils;

import java.io.Serializable;

/**
 * @author ninlgde
 * @date 2022/9/23 11:55
 */
public class Vector2 implements Serializable {
    private static final long serialVersionUID = 913902788239530931L;
    public static final Vector2 tmp = new Vector2();
    public static final Vector2 tmp2 = new Vector2();
    public static final Vector2 tmp3 = new Vector2();
    public static final Vector2 X = new Vector2(1.0F, 0.0F);
    public static final Vector2 Y = new Vector2(0.0F, 1.0F);
    public static final Vector2 Zero = new Vector2(0.0F, 0.0F);
    public float x;
    public float y;

    public Vector2() {
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 v) {
        this.set(v);
    }

    public Vector2 cpy() {
        return new Vector2(this);
    }

    public float len() {
        return (float) Math.sqrt((double) (this.x * this.x + this.y * this.y));
    }

    public float len2() {
        return this.x * this.x + this.y * this.y;
    }

    public Vector2 set(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
        return this;
    }

    public Vector2 set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2 sub(Vector2 v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    public Vector2 nor() {
        float len = this.len();
        if (len != 0.0F) {
            this.x /= len;
            this.y /= len;
        }

        return this;
    }

    public Vector2 add(Vector2 v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    public Vector2 add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public float dot(Vector2 v) {
        return this.x * v.x + this.y * v.y;
    }

    public Vector2 mul(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vector2 mul(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vector2 div(float value) {
        return this.mul(1.0F / value);
    }

    public Vector2 div(float vx, float vy) {
        return this.mul(1.0F / vx, 1.0F / vy);
    }

    public Vector2 div(Vector2 other) {
        return this.mul(1.0F / other.x, 1.0F / other.y);
    }

    public float dst(Vector2 v) {
        float x_d = v.x - this.x;
        float y_d = v.y - this.y;
        return (float) Math.sqrt((double) (x_d * x_d + y_d * y_d));
    }

    public float dst(float x, float y) {
        float x_d = x - this.x;
        float y_d = y - this.y;
        return (float) Math.sqrt((double) (x_d * x_d + y_d * y_d));
    }

    public float dst2(Vector2 v) {
        float x_d = v.x - this.x;
        float y_d = v.y - this.y;
        return x_d * x_d + y_d * y_d;
    }

    public float dst2(float x, float y) {
        float x_d = x - this.x;
        float y_d = y - this.y;
        return x_d * x_d + y_d * y_d;
    }

    public String toString() {
        return "[" + this.x + ":" + this.y + "]";
    }

    public Vector2 sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2 tmp() {
        return tmp.set(this);
    }

    public Vector2 mul(Matrix3 mat) {
        float x = this.x * mat.val[0] + this.y * mat.val[3] + mat.val[6];
        float y = this.x * mat.val[1] + this.y * mat.val[4] + mat.val[7];
        this.x = x;
        this.y = y;
        return this;
    }

    public float crs(Vector2 v) {
        return this.x * v.y - this.y * v.x;
    }

    public float crs(float x, float y) {
        return this.x * y - this.y * x;
    }

    public float angle() {
        float angle = (float) Math.atan2((double) this.y, (double) this.x) * 57.295776F;
        if (angle < 0.0F) {
            angle += 360.0F;
        }

        return angle;
    }

    public void setAngle(float angle) {
        this.set(this.len(), 0.0F);
        this.rotate(angle);
    }

    public Vector2 rotate(float degrees) {
        float rad = degrees * 0.017453292F;
        float cos = (float) Math.cos((double) rad);
        float sin = (float) Math.sin((double) rad);
        float newX = this.x * cos - this.y * sin;
        float newY = this.x * sin + this.y * cos;
        this.x = newX;
        this.y = newY;
        return this;
    }

    public Vector2 lerp(Vector2 target, float alpha) {
        Vector2 r = this.mul(1.0F - alpha);
        r.add(target.tmp().mul(alpha));
        return r;
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + NumberUtils.floatToIntBits(this.x);
        result = 31 * result + NumberUtils.floatToIntBits(this.y);
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Vector2 other = (Vector2) obj;
            if (NumberUtils.floatToIntBits(this.x) != NumberUtils.floatToIntBits(other.x)) {
                return false;
            } else {
                return NumberUtils.floatToIntBits(this.y) == NumberUtils.floatToIntBits(other.y);
            }
        }
    }

    public boolean epsilonEquals(Vector2 obj, float epsilon) {
        if (obj == null) {
            return false;
        } else if (Math.abs(obj.x - this.x) > epsilon) {
            return false;
        } else {
            return !(Math.abs(obj.y - this.y) > epsilon);
        }
    }
}
