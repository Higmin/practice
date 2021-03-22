package com.practice.algorithm.leetcode_passing;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * 输入：m = 3, n = 7
 * 输出：28
 * <p>
 * 示例 2：
 * 输入：m = 3, n = 2
 * 输出：3
 * <p>
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * <p>
 * 示例 3：
 * 输入：m = 7, n = 3
 * 输出：28
 * <p>
 * 示例 4：
 * 输入：m = 3, n = 3
 * 输出：6
 * <p>
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 109
 *
 * @author Jimmy
 * @version 1.0, 2021/03/22
 * @since practice 1.0.0
 */
public class N_62_UniquePaths {

    // m x n 的网格，可以看做是 [m][n] 的一个二维数组。
    // 采用动态规划：动态方程为： dp[m][n] = dp[m][n-1] + dp[m-1][n]
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // 动态方程下标防止交表越界，进行初始化。
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(7, 3));
        System.out.println(uniquePaths(3, 3));
    }
}
