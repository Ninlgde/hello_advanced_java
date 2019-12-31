package com.ninlgde.pearls;

import java.util.Map;

import static com.ninlgde.pearls.BitMap.BITS_PER_WORD;

public class BitSet {

    private long[] a;
    private int size;

    public BitSet(int cap) {
        size = (int) Math.ceil((double) cap / BITS_PER_WORD);
        a = new long[size];
    }

    public void set(int i) {
        checkArg(i);
        BitMap.set(a, i);
    }

    public void clear(int i) {
        checkArg(i);
        BitMap.clear(a, i);
    }

    public boolean exist(int i) {
        checkArg(i);
        long m = BitMap.test(a, i);
        return m != 0;
    }

    private void checkArg(int i) {
        if (i / BITS_PER_WORD > size) {
            throw new IllegalArgumentException("i过大");
        }
    }
}
