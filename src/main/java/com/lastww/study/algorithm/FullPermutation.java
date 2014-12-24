package com.lastww.study.algorithm;

/**
 * Created by liuweiwei on 14-12-24.
 * 全排列算法
 */
public class FullPermutation {

    public static int a[] = {1,2,3,4,5};

    public static int count = 0;

    public static void permutation(int[] a, int i) {

        if (i == -1) {
            for (int k = 0; k < a.length; k++) {
                System.out.print(a[k] + "\t");
            }
            System.out.print("\n");
            count++;
            return;
        }
        for (int j = i; j >= 0; j--) {
            a = swap(a, j, i);
            permutation(a, i - 1);
            a = swap(a, j, i);
        }

    }

    public static int[] swap(int[] a, int m, int n) {
        int tmp = a[m];
        a[m] = a[n];
        a[n] = tmp;
        return a;
    }

    public static void main(String[] args) {
        permutation(a, a.length - 1);
        System.out.println("total count:" + count);
    }
}
