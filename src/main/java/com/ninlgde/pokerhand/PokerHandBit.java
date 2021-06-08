package com.ninlgde.pokerhand;

/**
 * @author: ninlgde
 * @date: 2/3/21 3:14 PM
 */
public class PokerHandBit {

    /**
     * 获取一个64位long的二进制中为1的个数
     *
     * @param x the long
     * @return 1的个数
     */
    public static int bitCount64(long x) {
//    int xf = bitCount32(uint32_t(x >> 32));
//    int xb = bitCount32(uint32_t(x));
        //    return xf + xb;
        x = (x & 0x5555555555555555L) + ((x >> 1) & 0x5555555555555555L);
        x = (x & 0x3333333333333333L) + ((x >> 2) & 0x3333333333333333L);
        x = (x & 0x0F0F0F0F0F0F0F0FL) + ((x >> 4) & 0x0F0F0F0F0F0F0F0FL);
        x = (x & 0x00FF00FF00FF00FFL) + ((x >> 8) & 0x00FF00FF00FF00FFL);
        x = (x & 0x0000FFFF0000FFFFL) + ((x >> 16) & 0x0000FFFF0000FFFFL);
        x = (x & 0x00000000FFFFFFFFL) + ((x >> 32) & 0x00000000FFFFFFFFL);
        return (int) x;
    }

    /**
     * 获取一个32位int的二进制中为1的个数
     * @param x the int
     * @return 1的个数
     */
    public static int bitCount32(int x) {
        x = (x & 0x55555555) + ((x >> 1) & 0x55555555);
        x = (x & 0x33333333) + ((x >> 2) & 0x33333333);
        x = (x & 0x0F0F0F0F) + ((x >> 4) & 0x0F0F0F0F);
        x = (x & 0x00FF00FF) + ((x >> 8) & 0x00FF00FF);
        x = (x & 0x0000FFFF) + ((x >> 16) & 0x0000FFFF);
        return (int) x;
    }

    /**
     * 获取一个16位int的二进制中为1的个数
     * @param x the int
     * @return 1的个数
     */
    public static int bitCount16(int x) {
        x = (x & 0x5555) + ((x >> 1) & 0x5555);
        x = (x & 0x3333) + ((x >> 2) & 0x3333);
        x = (x & 0x0F0F) + ((x >> 4) & 0x0F0F);
        x = (x & 0x00FF) + ((x >> 8) & 0x00FF);
        return (int) x;
    }
}
