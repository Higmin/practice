package com.practice.algorithm.leetcode_passing;

/**
 * @author Higmin
 * @date 2020/3/29 19:13
 *
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 *
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 */
public class AddStrings {

    public static void main(String[] args) {
        String str1 = "9999990";
        String str2 = "11125444657";
        System.out.println(addStrings(str1, str2));
    }
    public static String addStrings(String num1, String num2) {
        // 思路：我们可以利用字符串 从后向前依次获取每个字符进行相加（注意考虑进位carry），然后生成一个新的字符串（计算一位加一位），最后通过字符串反转之后就是我们要的结果。
        // 既然用到字符串相加 和 反转，那么我们就考虑使用 StringBuilder。
        if(num1 == null || num1.equals("")) return num2;
        if(num2 == null || num2.equals("")) return num1;
        int length1 = num1.length()-1;
        int length2 = num2.length()-1;
        StringBuilder res = new StringBuilder();
        int carry = 0;
        while(length1 >= 0 || length2 >= 0){
            int n1 = 0, n2 = 0;
            if(length1 >= 0) {
                n1 = num1.charAt(length1--) - '0';
            }
            if(length2 >= 0) {
                n2 = num2.charAt(length2--) - '0';
            }
            int sum = (carry + n1 + n2) % 10;
            carry = (carry + n1 + n2) / 10;
            res = res.append(sum);
        }
        if(carry > 0) res.append(1);
        return res.reverse().toString();
    }
}
