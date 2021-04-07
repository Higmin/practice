package com.practice.algorithm.other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 无序数组中找到左侧比他小右侧比他大的数
 * <p>
 * 要求: 复杂度 小于O(n^2)
 *
 * @author Jimmy
 * @version 1.0, 2021/04/07
 * @since practice 1.0.0
 */
public class GreaterThanLeftAllAndLessThanRightAll {

    // 两次遍历可以实现
    public static List<Integer> greaterThanLeftAllAndLessThanRightAll(int[] nums) {
        List<Integer> result = new ArrayList<>();

        // 1. 第一次遍历找到满足情况一：左边的所有元素 > 当前元素 的元素集合
        // 左边的所有元素 > 当前元素 ==  左边的最小值 > 当前元素
        Queue<Integer> leftMax = new LinkedList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (min > nums[i]) {
                leftMax.add(i);
            }
            min = Math.min(min, nums[i]);
        }

        // 2. 第二次遍历找到满足情况二：当前元素 < 右边的所有元素 的元素集合
        // 当前元素 > 右边的所有元素 ==  当前元素 > 右边的最大元素
        Queue<Integer> rightMin = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > max) {
                rightMin.add(i);
            }
            max = Math.max(max, nums[i]);
        }

        // 3. 求并集
        while (!leftMax.isEmpty()) {
            Integer pop = leftMax.poll();
            if (rightMin.contains(pop)) {
                result.add(nums[pop]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(greaterThanLeftAllAndLessThanRightAll(new int[]{9, 8, 7, 3, 4, 2, 1}).toString());
        System.out.println(greaterThanLeftAllAndLessThanRightAll(new int[]{3, 3, 1}).toString());
    }
}
