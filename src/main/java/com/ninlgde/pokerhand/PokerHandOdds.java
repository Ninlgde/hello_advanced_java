package com.ninlgde.pokerhand;

import com.ninlgde.pokerhand.data.PokerHandData;

/**
 * @author: ninlgde
 * @date: 2/3/21 11:39 AM
 */
public class PokerHandOdds {

    /**
     * 循环计算比牌结果
     *
     * @param pocketMasks   手牌
     * @param shared        公共牌
     * @param dead          已翻出的牌
     * @param numberOfCards 一共的牌数量
     * @param playerNum     玩家数量
     * @return PokerHandResult 比牌统计结果.
     */
    public PokerHandResult Hands(long[] pocketMasks, long shared, long dead, int numberOfCards, int playerNum) {

        PokerHandResult result = new PokerHandResult(playerNum);

        int _i1, _i2, _i3, _i4, _i5;
        long _card1, _card2, _card3, _card4, _card5;
        long _n2, _n3, _n4;

        long boardHand;

        dead |= shared;

        switch (numberOfCards - PokerHandBit.bitCount64(shared)) {
            case 5:
                // 5张公共牌,没啥好说的,5层for循环搞定!
                for (_i1 = PokerHandData.NumberOfCards - 1; _i1 >= 0; _i1--) {
                    _card1 = PokerHandData.CardMasksTable[_i1];
                    if ((dead & _card1) != 0) continue;
                    for (_i2 = _i1 - 1; _i2 >= 0; _i2--) {
                        _card2 = PokerHandData.CardMasksTable[_i2];
                        if ((dead & _card2) != 0) continue;
                        _n2 = _card1 | _card2;
                        for (_i3 = _i2 - 1; _i3 >= 0; _i3--) {
                            _card3 = PokerHandData.CardMasksTable[_i3];
                            if ((dead & _card3) != 0) continue;
                            _n3 = _n2 | _card3;
                            for (_i4 = _i3 - 1; _i4 >= 0; _i4--) {
                                _card4 = PokerHandData.CardMasksTable[_i4];
                                if ((dead & _card4) != 0) continue;
                                _n4 = _n3 | _card4;
                                for (_i5 = _i4 - 1; _i5 >= 0; _i5--) {
                                    _card5 = PokerHandData.CardMasksTable[_i5];
                                    if ((dead & _card5) != 0) continue;
                                    boardHand = _n4 | _card5 | shared;
                                    // 根据手牌和for循环出来的公共牌,计算一下比牌结果
                                    // 结果存入PokerHandResult
                                    EvalHands(pocketMasks, boardHand, playerNum, result);
                                }
                            }
                        }
                    }
                }
                break;
            case 2:
                // 两张牌,遍历TwoCardTable,即可.
                for (_i1 = 0; _i1 < PokerHandData.TwoCardTable.length; _i1++) {
                    _card1 = PokerHandData.TwoCardTable[_i1];
                    if ((dead & _card1) != 0) continue;
                    boardHand = _card1 | shared;
                    EvalHands(pocketMasks, boardHand, playerNum, result);
                }
                break;
            case 1:
                // 一张牌,遍历CardMasksTable,即可.
                for (_i1 = 0; _i1 < PokerHandData.CardMasksTable.length; _i1++) {
                    _card1 = PokerHandData.CardMasksTable[_i1];
                    if ((dead & _card1) != 0) continue;
                    boardHand = _card1 | shared;
                    EvalHands(pocketMasks, boardHand, playerNum, result);
                }
                break;
        }
        return result;
    }

    /**
     * 比牌,比较牌力值吧.
     *
     * @param pocketMasks 手牌
     * @param boardHand   公共牌
     * @param playerNum   玩家数
     * @param result      PokerHandResult 比牌统计结果对象.
     */
    public void EvalHands(long[] pocketMasks, long boardHand, int playerNum, PokerHandResult result) {
        long[] pocketHands = new long[playerNum];
        int bestCount;
        // 拿第一个玩家的手牌的公共牌的牌力值当做最好的结果.
        long bestPocket = PokerHandEval.Evaluate(pocketMasks[0] | boardHand, 7);
        pocketHands[0] = bestPocket;
        bestCount = 1;
        for (int i = 1; i < playerNum; i++) {
            // 获取其他人的牌力值
            pocketHands[i] = PokerHandEval.Evaluate(pocketMasks[i] | boardHand, 7);
            // 如果牌力值比最好的结果好
            if (pocketHands[i] > bestPocket) {
                // 更新最好的结果,并设置最好的数量为1
                bestPocket = pocketHands[i];
                bestCount = 1;
            } else if (pocketHands[i] == bestPocket) {
                // 结果和最好的相同,最好的数量+1
                bestCount++;
            }
        }

        // 获取最好牌力值的牌型.
        int bestType = PokerHandEval.EvaluateHandType(bestPocket);
        result.types[bestType]++;
        result.typess.getAndIncrement(bestType);

        // 统计结果.
        for (int i = 0; i < playerNum; i++) {
            if (pocketHands[i] == bestPocket) {
                if (bestCount > 1) {
                    result.ties[i]++;
                    result.tiess.getAndIncrement(i);
                } else {
                    result.wins[i]++;
                    result.winss.getAndIncrement(i);
                }
            } else if (pocketHands[i] < bestPocket) {
                result.losses[i]++;
                result.lossess.getAndIncrement(i);
            }
        }

        result.totalHands++;
        result.totalHandss.getAndIncrement();
    }

    public boolean ValidateHand(String hand) {
        CardIndex index = new CardIndex(0);
        long handmask = 0L;
        int cards = 0;
        int card = 0;

        for (card = NextCard(hand, index); card >= 0; card = NextCard(hand, index)) {
            if ((handmask & (1L << card)) != 0)
                return false;
            handmask |= (1L << card);
            cards++;
        }

        return card == -1 && cards > 0 && index.index >= hand.length();
    }

    /**
     * 解析牌
     *
     * @param hand 牌值字符串 asadacah等
     * @return 牌值(1 < < 0 - 1 < < 51)
     */
    public long ParseHand(String hand) {
        CardIndex index = new CardIndex(0);
        int cards;
        long handmask = 0L;
        if (hand.length() == 0) {
            return 0L;
        }
//    if (!ValidateHand(cards)) return 0UL;
        cards = 0;
        for (int card = NextCard(hand, index); card >= 0; card = NextCard(hand, index)) {
            handmask |= (1L << card);
            cards++;
        }
        return handmask;
    }

    /**
     * 解析手牌和公共牌
     *
     * @param hand  手牌
     * @param board 公共牌
     * @return 牌值(1 < < 0 - 1 < < 51)
     */
    public long ParseHand(String hand, String board) {
        String cards = hand + board;
        return ParseHand(cards);
    }

    /**
     * 解析字符串的单张牌
     *
     * @param cards     牌字符串
     * @param cardIndex 字符串索引
     * @return 单张牌值(0 - 51)
     */
    public int NextCard(String cards, CardIndex cardIndex) {
        int rank = 0, suit = 0;
        int index = cardIndex.index;
        while (index < cards.length() && cards.charAt(index) == ' ')
            index = cardIndex.increase();

        if (index >= cards.length())
            return -1;

        // Parse cards
        if (index < cards.length()) {
            index = cardIndex.increase();
            switch (cards.charAt(index)) {
                case '1':
                    if (cards.charAt(index) == '0') {
                        index = cardIndex.increase();
                        rank = PokerHandData.Rank.RankT;
                    } else {
                        return -1;
                    }
                    break;
                case '2':
                    rank = PokerHandData.Rank.Rank2;
                    break;
                case '3':
                    rank = PokerHandData.Rank.Rank3;
                    break;
                case '4':
                    rank = PokerHandData.Rank.Rank4;
                    break;
                case '5':
                    rank = PokerHandData.Rank.Rank5;
                    break;
                case '6':
                    rank = PokerHandData.Rank.Rank6;
                    break;
                case '7':
                    rank = PokerHandData.Rank.Rank7;
                    break;
                case '8':
                    rank = PokerHandData.Rank.Rank8;
                    break;
                case '9':
                    rank = PokerHandData.Rank.Rank9;
                    break;
                case 'T':
                case 't':
                    rank = PokerHandData.Rank.RankT;
                    break;
                case 'J':
                case 'j':
                    rank = PokerHandData.Rank.RankJ;
                    break;
                case 'Q':
                case 'q':
                    rank = PokerHandData.Rank.RankQ;
                    break;
                case 'K':
                case 'k':
                    rank = PokerHandData.Rank.RankK;
                    break;
                case 'A':
                case 'a':
                    rank = PokerHandData.Rank.RankA;
                    break;
                default:
                    return -2;
            }
        } else {
            return -2;
        }

        if (index < cards.length()) {
            index = cardIndex.increase();
            switch (cards.charAt(index)) {
                case 'H':
                case 'h':
                    suit = PokerHandData.Suit.Hearts;
                    break;
                case 'D':
                case 'd':
                    suit = PokerHandData.Suit.Diamonds;
                    break;
                case 'C':
                case 'c':
                    suit = PokerHandData.Suit.Clubs;
                    break;
                case 'S':
                case 's':
                    suit = PokerHandData.Suit.Spades;
                    break;
                default:
                    return -2;
            }
        } else {
            return -2;
        }

        return rank + (suit * 13);
    }

    /**
     * 获取比牌统计结果.
     *
     * @param player 玩家手牌列表
     * @param playerNum 玩家数量
     * @param board 公共牌
     * @param dead 已翻的牌
     * @return 比牌结果 PokerHandResult
     */
    public PokerHandResult HandOdds(String[] player, int playerNum, String board, String dead) {
        long[] pokerMasks = new long[playerNum];
        // 解析已翻的牌
        long boardMask = 0, deadCardsMask = 0, deadCards = ParseHand(dead);

        deadCardsMask |= deadCards;

        // 解析玩家手牌
        for (int i = 0; i < playerNum; i++) {
            pokerMasks[i] = ParseHand(player[i], "");
            deadCardsMask |= pokerMasks[i];
        }

        // 解析公共牌
        boardMask = ParseHand("", board);

        // 统计结果
        return Hands(pokerMasks, boardMask, deadCardsMask, 5, playerNum);
    }

    private static class CardIndex {
        private int index;

        public CardIndex(int index) {
            this.index = index;
        }

        public int increase() {
            return index++;
        }
    }
}
