package com.ninlgde.pokerhand;

import com.ninlgde.pokerhand.data.*;

/**
 * @author: ninlgde
 * @date: 2/3/21 3:08 PM
 */
public class PokerHandEval {

    /**
     * 计算牌力值
     *
     * @param cards         7张牌的值,具体含义参考CardMasksTable
     * @param numberOfCards 牌的数量 比如7
     * @return 牌力值 = 牌型 << HANDTYPE_SHIFT | 最大牌/最大五张牌等其他值
     */
    public static long Evaluate(long cards, int numberOfCards) {
        long retval = 0;
        int four_mask, three_mask, two_mask;

        // 分花色计算有哪些牌值
        int sc = (int) ((cards >> (PokerHandData.CLUB_OFFSET)) & 0x1fffL);
        int sd = (int) ((cards >> (PokerHandData.DIAMOND_OFFSET)) & 0x1fffL);
        int sh = (int) ((cards >> (PokerHandData.HEART_OFFSET)) & 0x1fffL);
        int ss = (int) ((cards >> (PokerHandData.SPADE_OFFSET)) & 0x1fffL);

        // 获得一共出现的不同牌值
        int ranks = sc | sd | sh | ss;
        int n_ranks = PokerHandData3.nBitsTable[ranks];
        // 获得重复数量 重复数量 = 牌的数量 - 出现牌值的个数
        int n_dups = numberOfCards - n_ranks;

        // 检查顺子, 同花 和 同花顺, 这几种情况不同的牌值一定是会出现5张及以上的.
        if (n_ranks >= 5) {
            // 检查单花色的唯一牌值是否出现了5张以上
            if (PokerHandData3.nBitsTable[ss] >= 5) { // 黑桃
                // 再检查是否是顺子
                if (PokerHandData4.straightTable[ss] != 0)
                    // 都满足则是同花顺 返回 同花顺的值 + (顺子最大的牌<< TOP_CARD_SHIFT)
                    return PokerHandData.HANDTYPE_VALUE_STRAIGHTFLUSH + (PokerHandData4.straightTable[ss] << PokerHandData.TOP_CARD_SHIFT);
                else
                    // 不是顺子,则是同花 牌力值 = 同花的值 + 最大5张牌的值
                    retval = PokerHandData.HANDTYPE_VALUE_FLUSH + PokerHandData2.topFiveCardsTable[ss];
            } else if (PokerHandData3.nBitsTable[sc] >= 5) { // 梅花
                if (PokerHandData4.straightTable[sc] != 0)
                    return PokerHandData.HANDTYPE_VALUE_STRAIGHTFLUSH + (PokerHandData4.straightTable[sc] << PokerHandData.TOP_CARD_SHIFT);
                else
                    retval = PokerHandData.HANDTYPE_VALUE_FLUSH + PokerHandData2.topFiveCardsTable[sc];
            } else if (PokerHandData3.nBitsTable[sd] >= 5) { // 方片
                if (PokerHandData4.straightTable[sd] != 0)
                    return PokerHandData.HANDTYPE_VALUE_STRAIGHTFLUSH + (PokerHandData4.straightTable[sd] << PokerHandData.TOP_CARD_SHIFT);
                else
                    retval = PokerHandData.HANDTYPE_VALUE_FLUSH + PokerHandData2.topFiveCardsTable[sd];
            } else if (PokerHandData3.nBitsTable[sh] >= 5) { // 红桃
                if (PokerHandData4.straightTable[sh] != 0)
                    return PokerHandData.HANDTYPE_VALUE_STRAIGHTFLUSH + (PokerHandData4.straightTable[sh] << PokerHandData.TOP_CARD_SHIFT);
                else
                    retval = PokerHandData.HANDTYPE_VALUE_FLUSH + PokerHandData2.topFiveCardsTable[sh];
            } else {
                // 没有同花色的牌出现5张以上?
                // 检查是否是顺子
                int st = PokerHandData4.straightTable[ranks];
                if (st != 0)
                    // 是顺子 牌力值 = 顺子的值 + (顺子最大值 << TOP_CARD_SHIFT)
                    retval = PokerHandData.HANDTYPE_VALUE_STRAIGHT + (st << PokerHandData.TOP_CARD_SHIFT);
            }

            // 如果牌力值不为0,且重复值小于3,则返回牌力值
            // 牌力值不为零的检查是必要的,因为可能是>=5的散牌
            // 如果numberOfCards=7的话,n_dups一定小于3
            if (retval != 0 && n_dups < 3)
                return retval;
        }

    /*
     * By the time we're here, either:
     1) there's no five-card hand possible (flush or straight), or
     2) there's a flush or straight, but we know that there are enough
     duplicates to make a full house / quads possible.
     */
        // 检查重复牌数量
        switch (n_dups) {
            case 0:
                // 一张也没重复,且前面也不是同花,那比如是高牌了
                // 牌力值 = 高牌的值 + 最大的五张牌
                return PokerHandData.HANDTYPE_VALUE_HIGHCARD + PokerHandData2.topFiveCardsTable[ranks];
            case 1: {
                // 只重复了一张,一对,有什么好说的?
                int t, kickers;

                // 找出谁是一对的那张牌
                two_mask = ranks ^ (sc ^ sd ^ sh ^ ss);
                // 牌力值先把 一对的值加上
                retval = PokerHandData.HANDTYPE_VALUE_PAIR;
                // 再把 一对的牌值 << TOP_CARD_SHIFT 加上
                retval += (PokerHandData1.topCardTable[two_mask] << PokerHandData.TOP_CARD_SHIFT);
                // 单靠一对比不出谁大谁小,还需要其他四张牌
                // 获取排除掉一对以后的其他牌
                t = ranks ^ two_mask;
                // 获取其他最大的五张牌里最大的3张. 先左移4位,再把最后四位通过与给丢弃了.
                kickers = (PokerHandData2.topFiveCardsTable[t] >> PokerHandData.CARD_WIDTH) & ~PokerHandData.FIFTH_CARD_MASK;
                retval += kickers;
                return retval;
            }

            case 2:
                // 重复两张,三条或者两对
                // 先找出重复只一张的,如果存在,则为两对.
                two_mask = ranks ^ (sc ^ sd ^ sh ^ ss);
                if (two_mask != 0) {
                    // 找出两对以外的其他牌
                    int t = ranks ^ two_mask;
                    // 牌力值 = 两对的值
                    retval = PokerHandData.HANDTYPE_VALUE_TWOPAIR;
                    // 再加上 从最大五张牌里获取的两张对牌 + (最大的单牌 << THIRD_CARD_SHIFT)
                    retval += (PokerHandData2.topFiveCardsTable[two_mask]
                            & (PokerHandData.TOP_CARD_MASK | PokerHandData.SECOND_CARD_MASK))
                            + (PokerHandData1.topCardTable[t] << PokerHandData.THIRD_CARD_SHIFT);

                    return retval;
                } else { // 三条
                    int t, second;
                    // 获取三条的值
                    three_mask = ((sc & sd) | (sh & ss)) & ((sc & sh) | (sd & ss));
                    // 牌力值 = 三条的值
                    retval = PokerHandData.HANDTYPE_VALUE_TRIPS;
                    // 加上 三条的牌值 << TOP_CARD_SHIFT
                    retval += PokerHandData1.topCardTable[three_mask] << PokerHandData.TOP_CARD_SHIFT;
                    // 找出三条以外的值
                    t = ranks ^ three_mask; /* Only one bit set in three_mask */
                    second = PokerHandData1.topCardTable[t];
                    // 最大的单张值 << SECOND_CARD_SHIFT
                    retval += (second << PokerHandData.SECOND_CARD_SHIFT);
                    // 丢弃最大的单张
                    t ^= (1 << second);
                    // 第二大的单张 << THIRD_CARD_SHIFT
                    retval += PokerHandData1.topCardTable[t] << PokerHandData.THIRD_CARD_SHIFT;

                    return retval;
                }

            default:
                /* Possible quads, fullhouse, straight or flush, or two pair */
                // 其他情况,可能是四条, 葫芦, 三条对 等情况.
                // 先获取四条的牌值, 存在则必为四条
                four_mask = sh & sd & sc & ss;
                if (four_mask != 0) {
                    int tc = PokerHandData1.topCardTable[four_mask];
                    retval = PokerHandData.HANDTYPE_VALUE_FOUR_OF_A_KIND;
                    // 四条的值 + (四条牌 << TOP_CARD_SHIFT) + (除四条牌外最大的牌 << SECOND_CARD_SHIFT)
                    retval += (tc << PokerHandData.TOP_CARD_SHIFT)
                            + ((PokerHandData1.topCardTable[ranks ^ (1 << tc)]) << PokerHandData.SECOND_CARD_SHIFT);

                    return retval;
                }

                // 到这里,情况只剩 葫芦 和 三条对

                // 获取对子的牌值
                two_mask = ranks ^ (sc ^ sd ^ sh ^ ss);
                // 如果刚好和重复数量吻合,则为三条对,否则为葫芦.
                if (PokerHandData3.nBitsTable[two_mask] != n_dups) {
                    int tc, t;
                    // 获取三张牌的值
                    three_mask = ((sc & sd) | (sh & ss)) & ((sc & sh) | (sd & ss));
                    // 牌力值 = 葫芦的值
                    retval = PokerHandData.HANDTYPE_VALUE_FULLHOUSE;
                    tc = PokerHandData1.topCardTable[three_mask];
                    // + 三张的值 << TOP_CARD_SHIFT
                    retval += (tc << PokerHandData.TOP_CARD_SHIFT);
                    t = (two_mask | three_mask) ^ (1 << tc);
                    // + 最大对的值 << SECOND_CARD_SHIFT
                    retval += PokerHandData1.topCardTable[t] << PokerHandData.SECOND_CARD_SHIFT;
                    return retval;
                }

                if (retval != 0)
                    // 如果牌力值不为0,可能是同花和顺子在上面没返回,这里返回一下
                    return retval;
                else {
                    // 到这,只有可能是三条对(也就是两对)了.
                    int top, second;

                    // 两对的值
                    retval = PokerHandData.HANDTYPE_VALUE_TWOPAIR;
                    top = PokerHandData1.topCardTable[two_mask];
                    // 最大的对牌值 << TOP_CARD_SHIFT
                    retval += (top << PokerHandData.TOP_CARD_SHIFT);
                    second = PokerHandData1.topCardTable[two_mask ^ (1 << top)];
                    // 第二大的对 << SECOND_CARD_SHIFT
                    retval += (second << PokerHandData.SECOND_CARD_SHIFT);
                    // 从rank里排除前两对的值,获取到最大的即为那个单张, 然后 << THIRD_CARD_SHIFT
                    retval += (PokerHandData1.topCardTable[ranks ^ (1 << top) ^ (1 << second)]) << PokerHandData.THIRD_CARD_SHIFT;
                    return retval;
                }
        }
    }

    /**
     * 根据牌力值,获取牌型
     *
     * @param retval 牌力值
     * @return 牌型 0-9
     */
    public static int EvaluateHandType(long retval) {
        // 上面的牌型判断并没有计算皇家同花顺,因为只是同花顺的特殊情况.
        // 但是我们需要导出皇同的数量
        // 所以先获取牌型.
        long type = (retval & PokerHandData.HANDTYPE_MASK) >> PokerHandData.HANDTYPE_SHIFT;
        // 再判断如果牌型为StraightFlush,且TOP_CARD为RankA,则为皇同,type++
        if (type == PokerHandData.Type.StraightFlush && (retval & PokerHandData.TOP_CARD_MASK) >> PokerHandData.TOP_CARD_SHIFT == PokerHandData.Rank.RankA)
            type++;
        return (int) type;
    }
}
