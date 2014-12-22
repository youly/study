package com.lastww.study.algorithm;

/**
 * Created by liuweiwei on 14-12-22.
 * 0、1背包问题：背包指定容量下求能装下去的最大的商品价值
 */
public class Knapsack {

    /** 商品重量 */
    private static int[] w = {15, 15, 25};

    /** 商品价格 */
    private static int[] p = {2, 3, 3};

    /** 背包总容量 */
    private static int totalVolumn = 40;

    /**
     * 可用容量为av的情况下i件物品能装下去的最大价值
     * @param i
     * @param av
     * @return
     */
    public static int maxValue(int i, int av) {
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

        System.out.println("without_i=" + without_i + ", with_i=" + with_i + ", value=" + value);

        return value;

    }

    public static void main(String[] args) {
        System.out.println(maxValue(w.length -1, totalVolumn));
    }
}
