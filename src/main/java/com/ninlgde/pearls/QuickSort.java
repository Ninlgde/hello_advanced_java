package com.ninlgde.pearls;

public class QuickSort {

    public static void Swap(int[] A, int i, int j) {
        int tmp;
        tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void QuickSortDualPivot(int[] A, int L, int R) {

        if (L >= R) {
            return;
        }

        if (A[L] > A[R]) {
            Swap(A, L, R); //保证pivot1 <= pivot2
        }

        int pivot1 = A[L];
        int pivot2 = A[R];

        int i = L + 1;
        int k = L + 1;
        int j = R - 1;

        OUT_LOOP:
        while (k <= j) {
            if (A[k] < pivot1) {
                Swap(A, i, k);
                k++;
                i++;
            } else if (A[k] >= pivot1 && A[k] <= pivot2) {
                k++;
            } else {
                while (A[j] > pivot2) {
                    j--;
                    if (j < k) {//当k和j错过
                        break OUT_LOOP;
                    }
                }
                if (A[j] >= pivot1 && A[j] <= pivot2) {
                    Swap(A, k, j);
                    k++;
                } else {//A[j] < pivot1
                    Swap(A, j, k);//注意k不动
                }
                j--;
            }
        }
        i--;
        j++;
        Swap(A, L, i);//将pivot1交换到适当位置
        Swap(A, R, j);//将pivot2交换到适当位置

        //一次双轴切分至少确定两个元素的位置，这两个元素将整个数组区间分成三份
        QuickSortDualPivot(A, L, i - 1);
        QuickSortDualPivot(A, i + 1, j - 1);
        QuickSortDualPivot(A, j + 1, R);
    }
}
