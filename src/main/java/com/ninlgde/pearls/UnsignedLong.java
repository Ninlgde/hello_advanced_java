package com.ninlgde.pearls;

import java.nio.ByteBuffer;

public class UnsignedLong {
    public long high;
    public long low;

    private static final long MASK = 0xFFFFFFFFL;

    public UnsignedLong(double v) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.mark();
        buffer.putDouble(v);
        buffer.reset();
        high = buffer.getInt();
        low = buffer.getInt();
    }

    public UnsignedLong(long v) {
        high = v >> 32;
        low = v & MASK;
    }

    public UnsignedLong(long h, long l) {
        high = h & MASK;
        low = l & MASK;
    }

    public UnsignedLong add(UnsignedLong rhs) {
        long h, l;
        l = low + rhs.low;
        h = high + rhs.high + (low >> 32);
        return new UnsignedLong(h, l);
    }

    public UnsignedLong sub(UnsignedLong rhs) {
        long h, l;
        if (rhs.low > low) {
            l = (1L << 32) + low - rhs.low;
            h = (high - 1 - rhs.high) & MASK;
        } else {
            l = low - rhs.low;
            h = (high - rhs.high) & MASK;
        }
        return new UnsignedLong(h, l);
    }

    public UnsignedLong leftShift(int n) {
        long h, l;
        h = (high << 32 + low) << n >> 32 & MASK;
        l = (low << n) & MASK;
        return new UnsignedLong(h, l);
    }

    public UnsignedLong rightShift(int n) {
        long h, l;
        h = high << 32;
        l = (h + low) >> n & MASK;
        h = high >> n;
        return new UnsignedLong(h, l);
    }
}
