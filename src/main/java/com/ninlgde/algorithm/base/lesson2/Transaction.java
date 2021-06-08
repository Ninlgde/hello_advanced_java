package com.ninlgde.algorithm.base.lesson2;

/**
 * @author: ninlgde
 * @date: 2/4/21 5:04 PM
 */
public class Transaction implements Comparable<Transaction> {

    private final String who;
    private final Date date;
    private final double amount;

    public Transaction(String who, Date date, double amount) {
        this.who = who;
        this.date = date;
        this.amount = amount;
    }

    public Transaction(String transaction) {
        String[] d = transaction.split("\n");
        this.who = d[0];
        this.date = new Date(d[1]);
        this.amount = Double.parseDouble(d[2]);
    }

    public String who() {
        return who;
    }

    public Date date() {
        return date;
    }

    public double amount() {
        return amount;
    }

    @Override
    public String toString() {
        return who + "\n" + date.toString() + "\n" + amount;
    }

    @Override
    public boolean equals(Object obj) {
        return who.equals(((Transaction) obj).who) && date.equals(((Transaction) obj).date) && amount == ((Transaction) obj).amount;
    }

    @Override
    public int compareTo(Transaction o) {
        if (!who.equals(o.who))
            return who.compareTo(o.who);
        if (!date.equals(o.date))
            return date.compareTo(date);
        if (amount != o.amount)
            return (int) (amount - o.amount);
        return 0;
    }
}
