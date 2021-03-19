package com.practice.algorithm.leetcode_passing;

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * <p>
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 *
 * @author Jimmy
 * @version 1.0, 2021/03/19
 * @since practice 1.0.0
 */
public class N_198_HouseRobber {

    // 动态规划；
    // 画图分析可得状态方程为：dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
    // 即：要么就是 看第N个房屋的最大值，那么 就是比较 （第 N - 1 个房屋的最大值）和 （第 N - 2 个房屋的最大值 + 第 N 个的值）取其中最大的。
    public static int rob(int[] nums) {
        int dp[] = new int[nums.length];
        if (nums.length == 0) {
            return 0;
        }
        dp[0] = nums[0];

        if (nums.length == 1) {
            return dp[0];
        }
        dp[1] = Math.max(nums[0], nums[1]);

        if (nums.length == 2) {
            return dp[1];
        }

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] t01 = {2, 7, 9, 3, 1};
        System.out.println(rob(t01));
    }
}
