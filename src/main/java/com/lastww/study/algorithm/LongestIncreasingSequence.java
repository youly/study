package com.lastww.study.algorithm;

/**
 * Created by liuweiwei on 14-12-23.
 * 动态规划求最长增长子序列
 */
public class LongestIncreasingSequence {

    public static int[] a = {1,3,5,4,9,6,10,7,11,15,12,13};

    public static Integer[] memory = {};

    public static int callTimes = 0;

    public static Integer[][] result = {};

    /**
     * 求数组a中元素范围为0..n的最长增长子序列长度
     * @param a
     * @param n
     * @return
     */
    public static int maxLength(int[] a, int n) {
        if (n == 0) {
            result[n][0] = 1;
            return 1;
        }
        /** 避免重复调用 */
        if (memory[n] != 0) {
            //return memory[n];
        }
        callTimes++;
        int max = 1;
        for (int i = 0; i < n; i++) {
            if (a[n] > a[i]) {
                int length = maxLength(a, i) + 1;
                if (max < length) {
                    max = length;
                    result[i][n] = 1;
                }
            }
        }
        memory[n] = max;
        return max;
    }

    /** 存储中间结果 */
    public static void initMemory() {
        memory = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            memory[i] = 0;
        }
    }

    /** 最终结果矩阵 */
    public static void initResult() {
        result = new Integer[a.length][];
        for (int i = 0; i < a.length; i++) {
            result[i] = new Integer[a.length];
            for (int j = 0; j < a.length; j++ ) {
                result[i][j] = 0;
                if (i == j) {
                    result[i][j] = 1;
                }
            }
        }
    }

    /**
     * 打印最终结果矩阵
     */
    public static void printResult() {
        System.out.print("\t");
        for (int i = 0; i< a.length; i++) {
            System.out.print(a[i] + "\t");
        }
        System.out.print("\n");
        for (int i = 0; i < result.length; i++) {
            System.out.print(a[i] + "\t");
            for (int j = 0; j < a.length; j++ ) {
                System.out.print(result[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    /**
     * 打印最终序列
     */
    public static void printSequence() {
        System.out.print("sequence:");
        for (int i = 0; i < result.length; i++) {
            if (result[i][result.length - 1] == 1) {
                System.out.print(a[i] + "\t");
            }
        }
    }

    public static void main(String[] args) {
        initMemory();
        initResult();
        System.out.println("max length:" + maxLength(a, a.length - 1));
        System.out.println("callTimes:" + callTimes);
        printResult();
        printSequence();
    }
}
