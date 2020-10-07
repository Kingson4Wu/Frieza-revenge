package com.kxw.algorithm;

/**
 * https://leetcode-cn.com/problems/house-robber/solution/da-jia-jie-she-by-leetcode/
 */
public class DaJiaJieShe {

    public static void main(String[] args) {

        int[] arr = new int[] {6, 3, 4, 9, 11, 1};

        System.out.println(rob(arr));
    }

    public static int rob(int[] num) {
        int prevMax = 0;
        int currMax = 0;
        for (int x : num) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }

}
