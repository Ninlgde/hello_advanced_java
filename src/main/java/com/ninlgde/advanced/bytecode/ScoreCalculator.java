package com.ninlgde.advanced.bytecode;

public class ScoreCalculator {

    public void record(double score) {
    }

    public double getAverage() {
        return 0;
    }

    public int chooseNear(int i) {
        switch (i) {
            case 100: return 0;
            case 101: return 1;
            case 104: return 4;
            case 110: return 100;
            default: return -1;
        }
    }

    public static void main(String[] args) {
        ScoreCalculator calculator = new ScoreCalculator();

        int score1 = 1;
        int score2 = 2;

        calculator.record(score1);
        calculator.record(score2);

        double avg = calculator.getAverage();
    }

}