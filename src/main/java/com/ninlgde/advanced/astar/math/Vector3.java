package com.ninlgde.advanced.astar.math;

import com.ninlgde.advanced.astar.utils.NumberUtils;

import java.io.Serializable;

/**
 * @author ninlgde
 * @date 2022/9/23 11:57
 */
public class Vector3  implements Serializable {
    private static final long serialVersionUID = 3840054589595372522L;
    public float x;
    public float y;
    public float z;
    public static final Vector3 tmp = new Vector3();
    public static final Vector3 tmp2 = new Vector3();
    public static final Vector3 tmp3 = new Vector3();
    public static final Vector3 X = new Vector3(1.0F, 0.0F, 0.0F);
    public static final Vector3 Y = new Vector3(0.0F, 1.0F, 0.0F);
    public static final Vector3 Z = new Vector3(0.0F, 0.0F, 1.0F);
    public static final Vector3 Zero = new Vector3(0.0F, 0.0F, 0.0F);

    public Vector3() {
    }

    public Vector3(float x, float y, float z) {
        this.set(x, y, z);
    }

    public Vector3(Vector3 vector) {
        this.set(vector);
    }

    public Vector3(float[] values) {
        this.set(values[0], values[1], values[2]);
    }

    public Vector3 set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector3 set(Vector3 vector) {
        return this.set(vector.x, vector.y, vector.z);
    }

    public Vector3 set(float[] values) {
        return this.set(values[0], values[1], values[2]);
    }

    public Vector3 cpy() {
        return new Vector3(this);
    }

    public Vector3 tmp() {
        return tmp.set(this);
    }

    public Vector3 tmp2() {
        return tmp2.set(this);
    }

    Vector3 tmp3() {
        return tmp3.set(this);
    }

    public Vector3 add(Vector3 vector) {
        return this.add(vector.x, vector.y, vector.z);
    }

    public Vector3 add(float x, float y, float z) {
        return this.set(this.x + x, this.y + y, this.z + z);
    }

    public Vector3 add(float values) {
        return this.set(this.x + values, this.y + values, this.z + values);
    }

    public Vector3 sub(Vector3 a_vec) {
        return this.sub(a_vec.x, a_vec.y, a_vec.z);
    }

    public Vector3 sub(float x, float y, float z) {
        return this.set(this.x - x, this.y - y, this.z - z);
    }

    public Vector3 sub(float value) {
        return this.set(this.x - value, this.y - value, this.z - value);
    }

    public Vector3 mul(float value) {
        return this.set(this.x * value, this.y * value, this.z * value);
    }

    public Vector3 mul(Vector3 other) {
        return this.mul(other.x, other.y, other.z);
    }

    public Vector3 mul(float vx, float vy, float vz) {
        return this.set(this.x * vx, this.y * vy, this.z * vz);
    }

    public Vector3 div(float value) {
        return this.mul(1.0F / value);
    }

    public Vector3 div(float vx, float vy, float vz) {
        return this.mul(1.0F / vx, 1.0F / vy, 1.0F / vz);
    }

    public Vector3 div(Vector3 other) {
        return this.mul(1.0F / other.x, 1.0F / other.y, 1.0F / other.z);
    }

    public float len() {
        return (float)Math.sqrt((double)(this.x * this.x + this.y * this.y + this.z * this.z));
    }

    public float len2() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public boolean idt(Vector3 vector) {
        return this.x == vector.x && this.y == vector.y && this.z == vector.z;
    }

    public float dst(Vector3 vector) {
        float a = vector.x - this.x;
        float b = vector.y - this.y;
        float c = vector.z - this.z;
        a *= a;
        b *= b;
        c *= c;
        return (float)Math.sqrt((double)(a + b + c));
    }

    public Vector3 nor() {
        float len = this.len();
        return len == 0.0F ? this : this.div(len);
    }

    public float dot(Vector3 vector) {
        return this.x * vector.x + this.y * vector.y + this.z * vector.z;
    }

    public Vector3 crs(Vector3 vector) {
        return this.set(this.y * vector.z - this.z * vector.y, this.z * vector.x - this.x * vector.z, this.x * vector.y - this.y * vector.x);
    }

    public Vector3 crs(float x, float y, float z) {
        return this.set(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
    }

    public Vector3 mul(Matrix4 matrix) {
        float[] l_mat = matrix.val;
        return this.set(this.x * l_mat[0] + this.y * l_mat[4] + this.z * l_mat[8] + l_mat[12], this.x * l_mat[1] + this.y * l_mat[5] + this.z * l_mat[9] + l_mat[13], this.x * l_mat[2] + this.y * l_mat[6] + this.z * l_mat[10] + l_mat[14]);
    }

    public Vector3 prj(Matrix4 matrix) {
        float[] l_mat = matrix.val;
        float l_w = this.x * l_mat[3] + this.y * l_mat[7] + this.z * l_mat[11] + l_mat[15];
        return this.set((this.x * l_mat[0] + this.y * l_mat[4] + this.z * l_mat[8] + l_mat[12]) / l_w, (this.x * l_mat[1] + this.y * l_mat[5] + this.z * l_mat[9] + l_mat[13]) / l_w, (this.x * l_mat[2] + this.y * l_mat[6] + this.z * l_mat[10] + l_mat[14]) / l_w);
    }

    public Vector3 rot(Matrix4 matrix) {
        float[] l_mat = matrix.val;
        return this.set(this.x * l_mat[0] + this.y * l_mat[4] + this.z * l_mat[8], this.x * l_mat[1] + this.y * l_mat[5] + this.z * l_mat[9], this.x * l_mat[2] + this.y * l_mat[6] + this.z * l_mat[10]);
    }

    public boolean isUnit() {
        return this.len() == 1.0F;
    }

    public boolean isZero() {
        return this.x == 0.0F && this.y == 0.0F && this.z == 0.0F;
    }

    public Vector3 lerp(Vector3 target, float alpha) {
        Vector3 r = this.mul(1.0F - alpha);
        r.add(target.tmp().mul(alpha));
        return r;
    }

    public Vector3 slerp(Vector3 target, float alpha) {
        float dot = this.dot(target);
        if (!((double)dot > 0.99995D) && !((double)dot < 0.9995D)) {
            if (dot > 1.0F) {
                dot = 1.0F;
            }

            if (dot < -1.0F) {
                dot = -1.0F;
            }

            float theta0 = (float)Math.acos((double)dot);
            float theta = theta0 * alpha;
            Vector3 v2 = target.tmp().sub(this.x * dot, this.y * dot, this.z * dot);
            v2.nor();
            return this.mul((float)Math.cos((double)theta)).add(v2.mul((float)Math.sin((double)theta))).nor();
        } else {
            this.add(target.tmp().sub(this).mul(alpha));
            this.nor();
            return this;
        }
    }

    public String toString() {
        return this.x + "," + this.y + "," + this.z;
    }

    public float dot(float x, float y, float z) {
        return this.x * x + this.y * y + this.z * z;
    }

    public float dst2(Vector3 point) {
        float a = point.x - this.x;
        float b = point.y - this.y;
        float c = point.z - this.z;
        a *= a;
        b *= b;
        c *= c;
        return a + b + c;
    }

    public float dst2(float x, float y, float z) {
        float a = x - this.x;
        float b = y - this.y;
        float c = z - this.z;
        a *= a;
        b *= b;
        c *= c;
        return a + b + c;
    }

    public float dst(float x, float y, float z) {
        return (float)Math.sqrt((double)this.dst2(x, y, z));
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + NumberUtils.floatToIntBits(this.x);
        result = 31 * result + NumberUtils.floatToIntBits(this.y);
        result = 31 * result + NumberUtils.floatToIntBits(this.z);
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
            Vector3 other = (Vector3)obj;
            if (NumberUtils.floatToIntBits(this.x) != NumberUtils.floatToIntBits(other.x)) {
                return false;
            } else if (NumberUtils.floatToIntBits(this.y) != NumberUtils.floatToIntBits(other.y)) {
                return false;
            } else {
                return NumberUtils.floatToIntBits(this.z) == NumberUtils.floatToIntBits(other.z);
            }
        }
    }

    public boolean epsilonEquals(Vector3 obj, float epsilon) {
        if (obj == null) {
            return false;
        } else if (Math.abs(obj.x - this.x) > epsilon) {
            return false;
        } else if (Math.abs(obj.y - this.y) > epsilon) {
            return false;
        } else {
            return !(Math.abs(obj.z - this.z) > epsilon);
        }
    }

    public boolean epsilonEquals(float x, float y, float z, float epsilon) {
        if (Math.abs(x - this.x) > epsilon) {
            return false;
        } else if (Math.abs(y - this.y) > epsilon) {
            return false;
        } else {
            return !(Math.abs(z - this.z) > epsilon);
        }
    }

    public Vector3 scale(float scalarX, float scalarY, float scalarZ) {
        this.x *= scalarX;
        this.y *= scalarY;
        this.z *= scalarZ;
        return this;
    }
}
