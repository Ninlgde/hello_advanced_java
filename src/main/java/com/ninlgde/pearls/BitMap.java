package com.ninlgde.pearls;

public class BitMap {
    // long 占64bit
    public static final int BITS_PER_WORD = 64;
    // 64 = 2^6
    private static final int SHIFT = 6;
    // 64-1 = 0b111111
    private static final int MASK = 0x3f;

    public static void set(long[] a, int i) {
        a[i >> SHIFT] |= 1 << (i & MASK);
    }

    public static void clear(long[] a, int i) {
        a[i >> SHIFT] &= ~(1 << (i & MASK));
    }

    public static long test(long[] a, int i) {
        long m = a[i >> SHIFT];
        long n = (1 << (i & MASK));
        return m & n;
    }

    public static void main(String[] args) {
        long[] a = new long[10];
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
