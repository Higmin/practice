package com.practice.algorithm.leetcode_passing;

/**
 * 504. 七进制数
 * 给定一个整数，将其转化为7进制，并以字符串形式输出。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 100
 * 输出: "202"
 * 示例 2:
 * <p>
 * 输入: -7
 * 输出: "-10"
 * 注意: 输入范围是 [-1e7, 1e7] 。
 *
 * @author Jimmy
 * @version 1.0, 2021/03/02
 * @since practice 1.0.0
 */
public class N_504_Base7 {

    public static String convertToBase7(int num) {
        int temp = num;
        String result = "";
        while (temp != 0) {
            result = temp % 7 + result;
            temp = temp / 7;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(convertToBase7(100));
        System.out.println(convertToBase7(-7));
    }
}
