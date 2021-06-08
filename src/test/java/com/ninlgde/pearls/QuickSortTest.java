package com.ninlgde.pearls;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.ninlgde.pearls.QuickSort.QuickSortDualPivot;

public class QuickSortTest {

    @Test
    public void testQuickSortDualPivot() {
        int[] a;
        int[] r;

        a = new int[]{1};
        r = a.clone();
        QuickSortDualPivot(a, 0, a.length-1);
        Arrays.sort(r);
        Assert.assertArrayEquals(a, r);

        a = new int[]{2, 1};
        r = a.clone();
        QuickSortDualPivot(a, 0, a.length-1);
        Arrays.sort(r);
        Assert.assertArrayEquals(a, r);

        a = new int[]{1, 1};
        r = a.clone();
        QuickSortDualPivot(a, 0, a.length-1);
        Arrays.sort(r);
        Assert.assertArrayEquals(a, r);

        a = new int[]{1, 1, 1};
        r = a.clone();
        QuickSortDualPivot(a, 0, a.length-1);
        Arrays.sort(r);
        Assert.assertArrayEquals(a, r);

        a = new int[]{2, 1, 2};
        r = a.clone();
        QuickSortDualPivot(a, 0, a.length-1);
        Arrays.sort(r);
        Assert.assertArrayEquals(a, r);

        a = new int[]{1, 2, 3};
        r = a.clone();
        QuickSortDualPivot(a, 0, a.length-1);
        Arrays.sort(r);
        Assert.assertArrayEquals(a, r);

        a = new int[]{3, 2, 1};
        r = a.clone();
        QuickSortDualPivot(a, 0, a.length-1);
        Arrays.sort(r);
        Assert.assertArrayEquals(a, r);

        /*pivot1 = 3, pivot2 = 5, 而且没有3~5之间的元素*/
        a = new int[]{3, 1, 6, 2, 7, 5};
        r = a.clone();
        QuickSortDualPivot(a, 0, a.length-1);
        Arrays.sort(r);
        Assert.assertArrayEquals(a, r);

        /*pivot1 = 3, pivot2 = 5, 只有一个元素4在3到5之间*/
        a = new int[]{3, 1, 6, 4, 7, 5};
        r = a.clone();
        QuickSortDualPivot(a, 0, a.length-1);
        Arrays.sort(r);
        Assert.assertArrayEquals(a, r);

        /*pivot1 = 3, pivot2 = 5, 所有元素都大于5*/
        a = new int[]{3, 6, 7, 5};
        r = a.clone();
        QuickSortDualPivot(a, 0, a.length-1);
        Arrays.sort(r);
        Assert.assertArrayEquals(a, r);

        /*pivot1 = 3, pivot2 = 5, 所有元素都小于3*/
        a = new int[]{5, 2, 1, 3};
        r = a.clone();
        QuickSortDualPivot(a, 0, a.length-1);
        Arrays.sort(r);
        Assert.assertArrayEquals(a, r);
    }
}
