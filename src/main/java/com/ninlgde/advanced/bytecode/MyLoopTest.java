package com.ninlgde.advanced.bytecode;

public class MyLoopTest {
    public static int[] numbers = new int[]{1, 2, 3};
    public static void main(String[] args) {
        ScoreCalculator calculator = new ScoreCalculator();
        for (int number : numbers) {
            calculator.record(number);
        }
    }
}
