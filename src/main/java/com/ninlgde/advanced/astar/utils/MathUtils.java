package com.ninlgde.advanced.astar.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ninlgde
 * @date 2022/9/23 11:59
 */
public class MathUtils {
    public static final float PI = 3.1415927F;
    private static final int SIN_BITS = 13;
    private static final int SIN_MASK = 8191;
    private static final int SIN_COUNT = 8192;
    private static final float radFull = 6.2831855F;
    private static final float degFull = 360.0F;
    private static final float radToIndex = 1303.7972F;
    private static final float degToIndex = 22.755556F;
    public static final float radiansToDegrees = 57.295776F;
    public static final float radDeg = 57.295776F;
    public static final float degreesToRadians = 0.017453292F;
    public static final float degRad = 0.017453292F;
    private static final int ATAN2_BITS = 7;
    private static final int ATAN2_BITS2 = 14;
    private static final int ATAN2_MASK = 16383;
    private static final int ATAN2_COUNT = 16384;
    static final int ATAN2_DIM = (int) Math.sqrt(16384.0D);
    private static final float INV_ATAN2_DIM_MINUS_1;
    private static final int BIG_ENOUGH_INT = 16384;
    private static final double BIG_ENOUGH_FLOOR = 16384.0D;
    private static final double CEIL = 0.9999999D;
    private static final double BIG_ENOUGH_CEIL;
    private static final double BIG_ENOUGH_ROUND = 16384.5D;

    static {
        INV_ATAN2_DIM_MINUS_1 = 1.0F / (float) (ATAN2_DIM - 1);
        BIG_ENOUGH_CEIL = NumberUtils.longBitsToDouble(NumberUtils.doubleToLongBits(16385.0D) - 1L);
    }

    public MathUtils() {
    }

    public static float sin(float radians) {
        return MathUtils.Sin.table[(int) (radians * 1303.7972F) & 8191];
    }

    public static float cos(float radians) {
        return MathUtils.Cos.table[(int) (radians * 1303.7972F) & 8191];
    }

    public static float sinDeg(float degrees) {
        return MathUtils.Sin.table[(int) (degrees * 22.755556F) & 8191];
    }

    public static float cosDeg(float degrees) {
        return MathUtils.Cos.table[(int) (degrees * 22.755556F) & 8191];
    }

    public static float atan2(float y, float x) {
        float add;
        float mul;
        if (x < 0.0F) {
            if (y < 0.0F) {
                y = -y;
                mul = 1.0F;
            } else {
                mul = -1.0F;
            }

            x = -x;
            add = -3.1415927F;
        } else {
            if (y < 0.0F) {
                y = -y;
                mul = -1.0F;
            } else {
                mul = 1.0F;
            }

            add = 0.0F;
        }

        float invDiv = 1.0F / ((Math.max(x, y)) * INV_ATAN2_DIM_MINUS_1);
        int xi = (int) (x * invDiv);
        int yi = (int) (y * invDiv);
        return (MathUtils.Atan2.table[yi * ATAN2_DIM + xi] + add) * mul;
    }

    public static int random(int range) {
        return ThreadLocalRandom.current().nextInt(range + 1);
    }

    public static int random(int start, int end) {
        return start + ThreadLocalRandom.current().nextInt(end - start + 1);
    }

    public static boolean randomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public static float random() {
        return ThreadLocalRandom.current().nextFloat();
    }

    public static float random(float range) {
        return ThreadLocalRandom.current().nextFloat() * range;
    }

    public static float random(float start, float end) {
        return start + ThreadLocalRandom.current().nextFloat() * (end - start);
    }

    public static int nextPowerOfTwo(int value) {
        if (value == 0) {
            return 1;
        } else {
            --value;
            value |= value >> 1;
            value |= value >> 2;
            value |= value >> 4;
            value |= value >> 8;
            value |= value >> 16;
            return value + 1;
        }
    }

    public static boolean isPowerOfTwo(int value) {
        return value != 0 && (value & value - 1) == 0;
    }

    public static int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        } else {
            return Math.min(value, max);
        }
    }

    public static short clamp(short value, short min, short max) {
        if (value < min) {
            return min;
        } else {
            return value > max ? max : value;
        }
    }

    public static float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        } else {
            return Math.min(value, max);
        }
    }

    public static int floor(float x) {
        return (int) ((double) x + 16384.0D) - 16384;
    }

    public static int floorPositive(float x) {
        return (int) x;
    }

    public static int ceil(float x) {
        return (int) ((double) x + BIG_ENOUGH_CEIL) - 16384;
    }

    public static int ceilPositive(float x) {
        return (int) ((double) x + 0.9999999D);
    }

    public static int round(float x) {
        return (int) ((double) x + 16384.5D) - 16384;
    }

    public static int roundPositive(float x) {
        return (int) (x + 0.5F);
    }

    private static class Atan2 {
        static final float[] table = new float[16384];

        static {
            for (int i = 0; i < MathUtils.ATAN2_DIM; ++i) {
                for (int j = 0; j < MathUtils.ATAN2_DIM; ++j) {
                    float x0 = (float) i / (float) MathUtils.ATAN2_DIM;
                    float y0 = (float) j / (float) MathUtils.ATAN2_DIM;
                    table[j * MathUtils.ATAN2_DIM + i] = (float) Math.atan2(y0, x0);
                }
            }

        }

        private Atan2() {
        }
    }

    private static class Cos {
        static final float[] table = new float[8192];

        static {
            int i;
            for (i = 0; i < 8192; ++i) {
                table[i] = (float) Math.cos(((float) i + 0.5F) / 8192.0F * 6.2831855F);
            }

            for (i = 0; i < 360; i += 90) {
                table[(int) ((float) i * 22.755556F) & 8191] = (float) Math.cos((float) i * 0.017453292F);
            }

        }

        private Cos() {
        }
    }

    private static class Sin {
        static final float[] table = new float[8192];

        static {
            int i;
            for (i = 0; i < 8192; ++i) {
                table[i] = (float) Math.sin(((float) i + 0.5F) / 8192.0F * 6.2831855F);
            }

            for (i = 0; i < 360; i += 90) {
                table[(int) ((float) i * 22.755556F) & 8191] = (float) Math.sin((float) i * 0.017453292F);
            }

        }

        private Sin() {
        }
    }
}
