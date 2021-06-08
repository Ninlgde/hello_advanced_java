package com.ninlgde.pokerhand;

/**
 * @author: ninlgde
 * @date: 2/3/21 5:52 PM
 */
public class Main {

    public static void main(String[] args) {
        PokerHandOdds pho = new PokerHandOdds();
        String[] pockets = {"asah", "2d2c"};

        PokerHandResult result = null;
        double average = 0;
        for (int i = 0; i < 15; i++) {
            long start = System.nanoTime();
            result = pho.HandOdds(pockets, pockets.length, "", "");
            long used = System.nanoTime() - start;

            double elapsedTimeInSecond = (double) used / 1_000_000_000;

            average += elapsedTimeInSecond;
            System.out.println(elapsedTimeInSecond + " seconds");

        }

        average /= 15;

        System.out.println("avg time cost " + average + " seconds");

        System.out.println(result);
    }
}
