package com.kxw.interview;

/**
 * Created by kingsonwu on 18/3/30.
 * 判断一个无序数列是否是等差数列
 *
 * 　　（1）对数组进行第一次遍历，找出数组中的min.max
 *
 * 　　（2）如果是等差数列，那么公差必然是（max-min）/(n-1)   n为元素个数
 *
 * 　　（3）有了公差，有了首项，有了尾项。这个等差数列实际上就模拟出来了。
 */
public class ArithmeticProgressionJudge {

    //如果公差为0，那么说明数列中所有的数都是相同的 ，判断所有数是否相同
    //公差不为0

    public static void main(String[] args) {

        int[] seq = {2, 4, 8, 6};
        System.out.println(judge(seq));

    }

    public static boolean judge(int[] arr) {

        int min = arr[0], max = arr[0];
        for (int i = 1; i < arr.length; i++) {//对数组进行第一次遍历,找出数组中min ,max

            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] > max) {
                max = arr[i];
            }
        }

        int dif = (max - min) / (arr.length - 1);

        if (dif == 0) {//如果公差为0，那么说明数列中所有的数都是相同的 ，判断所有数是否相同
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != min) {
                    return false;
                }
            }
        } else {
            int[] positionArr = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                int position = (arr[i] - min) / dif;
                if (position >= arr.length || positionArr[position] == 1) {
                    return false;
                } else {
                    positionArr[position] = 1;
                }
            }
            return true;

        }
        return true;
    }

}
