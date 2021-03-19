package com.practice.algorithm.leetcode_passing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,2,3]
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * @author Jimmy
 * @version 1.0, 2021/03/19
 * @since practice 1.0.0
 */
public class N_169_MajorityElement {

    // 如果将数组 nums 中的所有元素按照单调递增或单调递减的顺序排序，那么下标为 n / 2 的元素（下标从 0 开始）一定是众数。
    public static int majorityElement(int[] nums) {
        int result = -1;
        if (nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);
        result = nums[nums.length / 2];
        return result;
    }

    // 使用哈希表来存储每个元素出现的次数，然后遍历哈希表找出出现次数大于 n / 2 次的元素
    public static int majorityElement2(int[] nums) {
        int result = -1;
        if (nums.length == 0) {
            return result;
        }

        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int cur : nums) {
            if (counts.containsKey(cur)) {
                counts.put(cur, counts.get(cur) + 1);
            } else {
                counts.put(cur, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > nums.length / 2) {
                result = entry.getKey();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] test01 = {1};
        int[] test02 = {3, 2, 3};
        int[] test03 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement(test01));
        System.out.println(majorityElement(test02));
        System.out.println(majorityElement(test03));

        System.out.println(majorityElement2(test01));
        System.out.println(majorityElement2(test02));
        System.out.println(majorityElement2(test03));
    }
}
