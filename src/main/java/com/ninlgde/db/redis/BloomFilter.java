package com.ninlgde.db.redis;

import com.ninlgde.pearls.BitSet;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class BloomFilter {
    // k 向上取整
    private final int ik;
    // m 向上取整
    private final int im;
    // 大于m的2次幂
    private int mp = 1;

    // ln(2)
    private static final double ln2 = 0.6931471805599453;
    // ln(2)^2
    private static final double ln2p2 = 0.4804530139182014;

    // bit set 存放数据
    private final BitSet bitSet;

    // hash functions
    private final List<HashFunction> hashFunctions = new ArrayList<>();

    /**
     * bloom过滤
     *
     * @param n 传入集合的大小
     * @param p 要求的误差率
     */
    public BloomFilter(long n, double p) {
        // m = - (n * ln(p) / (ln(2) ^ 2))
        double m = -(n * Math.log(p) / ln2p2);
        if (m / 64 > Integer.MAX_VALUE) {
            // bit map 是long[] 64bit 数组最大值为Integer.MAX_VALUE 所以超过 这俩值相乘,无法正常运算
            throw new IllegalArgumentException("n过大");
        }
        // k = ln(2) * m / n
        double k = ln2 * m / n;

        ik = (int) Math.ceil(k);
        im = (int) Math.ceil(m);

        // 找到大于m的2次幂
        int iim = im;
        while (iim > 0) {
            iim = iim >> 1;
            mp <<= 1;
        }

        // 创建bit set
        bitSet = new BitSet(im);

        // 构造hash function
        for (int i = 0; i < ik; i++) {
            int seed = ThreadLocalRandom.current().nextInt();
            hashFunctions.add(Hashing.murmur3_32(seed));
        }
    }

    public void add(String value) {
        for (int i = 0; i < ik; i++) {
            int h = hash(value, i);
            bitSet.set(h);
        }
    }

    public boolean exists(String value) {
        boolean result = true;
        for (int i = 0; i < ik; i++) {
            int h = hash(value, i);
            result = result && bitSet.exist(h);
        }
        return result;
    }


    // 10% 需要3.32 1%需要6.64 0.1%需要9.96 0.01%需要13.28 0.001%需要16.60
    // 现在有17个,支持的概率应该为100%-0.0008%
    // 其实也可以在构造函数里随机获取数字,不过效果差不多.
    private final int[] seeds = new int[]{3, 5, 7, 11, 13, 17, 23, 29, 31, 33, 37, 45, 48, 51, 57, 61, 63};

    private int hash(String value, int i) {
        HashCode hascode = hashFunctions.get(i).hashString(value, StandardCharsets.UTF_8);
        return ((mp - 1) & hascode.asInt()) % im;
    }

    private int hash2(String value, int ii) {
        int result = 0;
        int seed = seeds[ii];
        int length = value.length();
        for (int i = 0; i < length; i++) {
            result = seed * result + value.charAt(i);
        }
        return ((mp - 1) & result) % im;
    }
}
