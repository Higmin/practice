package com.practice.algorithm.leetcode_passing;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * 示例 1：
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * <p>
 * 示例 2：
 * 输入：prices = [7,6,4,3,1]
 * 输出 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * <p>
 * 提示：
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 *
 * @author Jimmy
 * @version 1.0, 2021/03/03
 * @since practice 1.0.0
 */
public class N_121_BestTimeToBuyAndSellStock {

    public static int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int result = 0; // 如果你不能获取任何利润，返回 0, 所以利润最小赋值为0。
        for (int p : prices) {
            min = Math.min(min, p);
            result = Math.max(result, p - min);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));
    }
}
