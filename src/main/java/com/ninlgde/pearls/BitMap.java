package com.ninlgde.pearls;

import java.nio.ByteBuffer;

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

    static int add1(Integer a) {
        return ++a;
    }


    public static void bufferMove(ByteBuffer buffer, int dest, int src, int length) {
        // todo: optimize
        if (src > dest) {
            buffer.position(dest);
            while (length-- > 0)
                buffer.put(buffer.get(src++));
            return;
        } else if (src < dest){
            buffer.position(dest+length);
            while (length-- > 0)
                buffer.put(dest+length, buffer.get(src+(length)));
        } else
            // no move
            buffer.position(dest+length);
    }

    public static void main(String[] args) {
        long[] a = new long[10];
        int idx = 88;
        Integer ch = idx ++;
        System.out.println(ch);
        System.out.println(idx);
        ch = add1(ch);
        System.out.println(ch);
        int n = 3;
        while (n-- > 0)
            System.out.println('a');
        byte[] s_to_hex = new byte[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb = new StringBuilder();
        sb.append(new String(s_to_hex));
        System.out.println(sb.toString());
        byte[] bytes = sb.toString().getBytes();
        System.out.println(bytes);
        int b = Integer.MIN_VALUE;
        System.out.println(b);
        b = ~b + 1;
        System.out.println(b);

        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put(bytes);
        bufferMove(buffer, 5, 4, 4);
        System.out.println(buffer);

        long f = 17085228768846925L;
        f <<=1;
        f <<=1;
        f <<=1;
        f <<=1;
        f <<=1;
        f <<=1;
        f <<=1;
        f <<=1;
        f <<=1;
        f <<=1;
//        BitMap.set(a, idx);
//        BitMap.set(a, 87);
//        System.out.println(BitMap.test(a, idx));
//        System.out.println(BitMap.test(a, 87));
//        BitMap.clear(a, idx);
//        System.out.println(BitMap.test(a, idx));
//        System.out.println(BitMap.test(a, 87));
        f >>>=1;
        f &= 0xffffL;


    }
}
