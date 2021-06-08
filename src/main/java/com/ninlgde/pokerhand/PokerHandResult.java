package com.ninlgde.pokerhand;

import com.ninlgde.pokerhand.data.PokerHandData;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/**
 * @author: ninlgde
 * @date: 2/3/21 11:41 AM
 */
public class PokerHandResult {
    public long[] wins;
    public long[] losses;
    public long[] ties;
    public long totalHands;
    public long[] types;

    public AtomicLongArray winss;
    public AtomicLongArray lossess;
    public AtomicLongArray tiess;
    public AtomicLong totalHandss;
    public AtomicLongArray typess;

    public PokerHandResult(int playerNum) {
        wins = new long[playerNum];
        losses = new long[playerNum];
        ties = new long[playerNum];
        totalHands = 0;
        types = new long[10];

        winss = new AtomicLongArray(playerNum);
        lossess = new AtomicLongArray(playerNum);
        tiess = new AtomicLongArray(playerNum);
        totalHandss = new AtomicLong(0);
        typess = new AtomicLongArray(10);
    }

    @Override
    public String toString() {
        StringBuilder wb = new StringBuilder("wins:[");
        for (long w : wins) {
            wb.append(w).append(',');
        }
        wb.append("]\n\n");
        StringBuilder lb = new StringBuilder("losses:[");
        for (long w : losses) {
            lb.append(w).append(',');
        }
        lb.append("]\n\n");
        StringBuilder tb = new StringBuilder("ties:[");
        for (long w : ties) {
            tb.append(w).append(',');
        }
        tb.append("]\n\n");
        StringBuilder tpb = new StringBuilder("types:\n");
        for (int i = 0; i < types.length; i++) {
            String name = PokerHandData.TYPES[i];
            long count = types[i];
            tpb.append(name).append(": ").append(count).append("\n");
        }
        tpb.append("\n");
        return wb.toString() +
                lb.toString() +
                tb.toString() +
                tpb.toString() +
                "totalHands:" + totalHands;
    }
}
