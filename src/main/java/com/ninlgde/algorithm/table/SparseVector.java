package com.ninlgde.algorithm.table;

/**
 * @author: ninlgde
 * @date: 2/19/21 2:06 PM
 */
public class SparseVector {
    private Table<Integer, Double> st;

    public SparseVector() {
        st = new SeparateChainingHashTable<>();
    }

    public int size() {
        return st.size();
    }

    public void put(int i, double x) {
        st.put(i, x);
    }

    public double get(int i) {
        if (!st.contains(i))
            return 0.0;

        return st.get(i);
    }

    public double dot(double[] that) {
        double sum = 0.0;
        for (int i : st.keys()) {
            sum += that[i] * this.get(i);
        }
        return sum;
    }
}
