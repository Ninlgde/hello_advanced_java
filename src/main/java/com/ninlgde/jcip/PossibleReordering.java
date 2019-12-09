package com.ninlgde.jcip;

public class PossibleReordering {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args)
            throws InterruptedException {
        int i = 0;
        do {
            x = y = a = b = 0;

            Thread one = new Thread(() -> {
                a = 1;
                x = b;
            });

            Thread two = new Thread(() -> {
                b = 1;
                y = a;
            });

            two.start();
            one.start();
            two.join();
            one.join();

            i++;
            System.out.println("第" + i + "次尝试( " + x + "," + y + " )");

        } while (x != 0 || y != 0);
    }
}
