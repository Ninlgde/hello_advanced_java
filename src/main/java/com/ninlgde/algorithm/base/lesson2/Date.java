package com.ninlgde.algorithm.base.lesson2;

/**
 * @author: ninlgde
 * @date: 2/4/21 4:39 PM
 */
public class Date implements Comparable<Date> {

    private final int year, month, day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date(String date) {
        String[] d = date.split("/");
        this.year = Integer.parseInt(d[0]);
        this.month = Integer.parseInt(d[1]);
        this.day = Integer.parseInt(d[2]);
    }

    public int mouth() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }

    @Override
    public String toString() {
        return year + "/" + month + "/" + day;
    }

    @Override
    public boolean equals(Object obj) {
        return year == ((Date) obj).year && month == ((Date) obj).month && day == ((Date) obj).day;
    }

    @Override
    public int compareTo(Date o) {
        if (year != o.year)
            return year - o.year;
        if (month != o.month)
            return month - o.month;
        if (day != o.day)
            return day - o.day;
        return 0;
    }
}
