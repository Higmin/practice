package com.practice.algorithm.leetcode_passing;

/**
 * 5. 最长回文子串
 * <p>
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * 示例 3：
 * 输入：s = "a"
 * 输出："a"
 * <p>
 * 示例 4：
 * 输入：s = "ac"
 * 输出："a"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 *
 * @author Jimmy
 * @version 1.0, 2021/03/26
 * @since practice 1.0.0
 */
public class N_5_LongestPalindromicSubstring {

    // 1. 本题可以采用动态规划；如果从脚标 l 到脚标 r 是一个 回文串，那么减去两端之后（这里两端相等），还是一个回文串，那么就有以下公式
    // dp[l][r] = dp[l+1][r-1] && s.charAt(l) == s.charAt(r)

    // 2. 这个动态规划公式中，用到了二维数组（矩阵），那么我们需要考虑矩阵的遍历。
    // 3. 根据公式可以看出，dp[l][r] = dp[l+1][r-1].......，第l 行，r列的值，依赖于 第 l+1 行，r-1列的值。
    // 所以我们需要在计算 dp[l][r] 之前 计算出 dp[l+1][r-1]的值。 ----> 也就是说：r-1 在前，r 在后。
    // r表示列，所以我们采用列优先，来遍历整个矩阵，以保证每次计算公式时，需要用到前面的数已经算出来了。
    // 在这儿多一嘴： 列优先 ====> 双重for 中的内层的 for 表示列。

    // 4. l < r 时，我们要按照公式计算对应位置的值。
    // 由于可能会用到无效的数据（矩阵左下角：l >= r ,不符合要求。）。所以需要把无效的数据置为true，防止影响计算结果。

    // 5.最后把满足要求的脚标，截取出来字符串，并取最大值，就是我们需要的结果。

    public static String longestPalindrome(String s) {
        String result = "";

        int maxLength = 1;
        int length = s.length();
        if (length <= 1) {
            return s;
        }

        result = s.substring(0, 1);
        boolean[][] dp = new boolean[length][length];
        for (int r = 0; r < length; r++) {
            for (int l = 0; l < length; l++) {

                if (l >= r) {
                    dp[l][r] = true;
                } else {
                    dp[l][r] = dp[l + 1][r - 1] && s.charAt(l) == s.charAt(r);
                }

                if (dp[l][r] && r - l + 1 > maxLength) {
                    maxLength = r - l + 1;
                    result = s.substring(l, r + 1);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("bb"));
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("aaaa"));
        System.out.println(longestPalindrome("cbbd"));
    }
}
