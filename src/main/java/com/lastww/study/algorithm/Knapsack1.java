package com.lastww.study.algorithm;

/**
 * Created by liuweiwei on 14-12-22.
 * 0、1背包问题：背包指定容量下求能装下去的最大的商品价值
 * 存储中间结果
 */
public class Knapsack1 {

    /** 商品重量 */
    private static int[] w = {1,2,3,5,6,7,8,9,10,11,12,13,14,15,16,17,18,20};

    /** 商品价格 */
    private static int[] p = {10,9,3,6,7,11,2,1,1,4,11,2,3,6,7,8,9,10,2,1};

    /** 背包总容量 */
    private static int totalVolumn = 100;

    /** 装下物品结果 */
    private static int[] result = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    private static Integer[][] memory = {};

    private static int callTimes = 0;

    /**
     * 可用容量为av的情况下i件物品能装下去的最大价值
     * @param i
     * @param av
     * @return
     */
    public static int maxValue(int i, int av) {
        callTimes++;
        if (memory[i][av] != -1) {
            return memory[i][av];
        }
        System.out.println("i=" + i + ", av=" + av);
        if (i == 0) {
            if (av >= w[0]) {
                return p[0];
            } else {
                return 0;
            }
        }
        int without_i = maxValue(i - 1, av);

        // 装不下 w[i]，直接返回
        if (av < w[i]) {
            return without_i;
        }
        int with_i = maxValue(i - 1, av - w[i]) + p[i];

        int value = Math.max(without_i, with_i);

        if (with_i > without_i) {
            result[i] = 1;
        }

        memory[i][av] = value;


        //System.out.println("without_i=" + without_i + ", with_i=" + with_i + ", value=" + value);

        return value;

    }

    public static void initMemory() {
        memory = new Integer[w.length][];
        for (int i = 0; i < w.length; i++) {
            memory[i] = new Integer[totalVolumn + 1];
            for (int j = 0; j <= totalVolumn; j++ ) {
                memory[i][j] = -1;
            }
        }
    }

    public static void main(String[] args) {

        initMemory();
        System.out.println("max value:" + maxValue(w.length -1, totalVolumn));
        for (int i : result) {
            //System.out.println("i=" + i + ", selected? " + (result[i] == 1 ? "yes" : "no"));
        }
        System.out.println("call times:" + callTimes);

    }
}
