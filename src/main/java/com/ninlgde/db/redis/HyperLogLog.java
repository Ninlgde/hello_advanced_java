package com.ninlgde.db.redis;

public class HyperLogLog {
    private int k;
    private BitKeeper[] keepers;

    public HyperLogLog() {
        this(1024);
    }

    public HyperLogLog(int k) {
        this.k = k;
        this.keepers = new BitKeeper[k];
        for (int i = 0; i < k; i++) {
            this.keepers[i] = new BitKeeper();
        }
    }

    public void add(long m) {
        BitKeeper keeper = keepers[(int) (((m & 0xffff000) >> 12) % keepers.length)];
        keeper.random(m);
    }

    public double count() {
        double sumbitsInverse = 0.0;
        int validKeeper = 0;
        for (BitKeeper keeper : keepers) {
            if (keeper.maxbits != 0) {
                sumbitsInverse += 1.0 / (float) keeper.maxbits;
                validKeeper ++;
            }
//            System.out.printf("%d %.2f %d\n",i++, sumbitsInverse, keeper.maxbits);
        }
//        System.out.printf("%d %.2f\n",validKeeper, sumbitsInverse);
        double avgBits = (float) validKeeper / sumbitsInverse;
        return Math.pow(2, avgBits) * this.k;
    }

    private static class BitKeeper {
        private int maxbits;

        public void random(long value) {
            int bits = lowZeros(value);
            if (bits > this.maxbits) {
                this.maxbits = bits;
            }
        }

        private int lowZeros(long value) {
            int i = 1;
            for (; i < 32; i++) {
                if (value >> i << i != value) {
                    break;
                }
            }
            return i - 1;
        }
    }
}
