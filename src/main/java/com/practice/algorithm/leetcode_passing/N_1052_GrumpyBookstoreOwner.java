package com.practice.algorithm.leetcode_passing;

/**
 * 1052. 爱生气的书店老板
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 * <p>
 * 示例：
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：
 * 书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * <p>
 * 提示：
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 *
 * @author Jimmy
 * @version 1.0, 2021/02/26
 * @since practice 1.0.0
 */
public class N_1052_GrumpyBookstoreOwner {

    // 思路：拆解问题 -> 最多有多少客户能够感到满意的数量
    // 1.由于已经满意的顾客是一定要算到里面的，那我们不妨先把原本就满意的顾客数量加起来，同时将对应的customers[i] 变为 0。
    // 2.之后的问题转化为：在 customers 中找到连续一段长度为 x 的子数组，使得其总和最大(利用滑动窗口实现)。这部分就是我们应用技巧所得到的客户。
    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int result = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                result += customers[i];
                customers[i] = 0;
            }
        }
        int left = 0;
        int max = 0, cur = 0;
        for (int right = 0; right < customers.length; right++) {
            if (right - left + 1 <= X) {
                cur = cur + customers[right];
            } else {
                cur = cur + customers[right] - customers[left++];
            }
            max = Math.max(max, cur);
        }
        return result + max;
    }

    public static void main(String[] args) {
        System.out.println(maxSatisfied(new int[]{2, 6, 6, 9}, new int[]{0, 0, 1, 1}, 1));
    }
}
