package com.ninlgde.pearls;

public class BitMap {
    public static final int BITS_PER_WORD = 32;
    private static final int SHIFT = 5;
    private static final int MASK = 0x1f;

    public static void set(int[] a, int i) {
        a[i >> SHIFT] |= 1 << (i & MASK);
    }

    public static void clear(int[] a, int i) {
        a[i >> SHIFT] &= ~(1 << (i & MASK));
    }

    public static int test(int[] a, int i) {
        return a[i >> SHIFT] & (1 << (i & MASK));
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        int idx = 88;
        BitMap.set(a, idx);
        BitMap.set(a, 87);
        System.out.println(BitMap.test(a, idx));
        System.out.println(BitMap.test(a, 87));
        BitMap.clear(a, idx);
        System.out.println(BitMap.test(a, idx));
        System.out.println(BitMap.test(a, 87));
    }
}
