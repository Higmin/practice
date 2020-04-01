package com.practice.algorithm.leetcode_passing;

import java.util.PriorityQueue;

/**
 * @author Higmin
 * @date 2020/4/01 21:06
 *
 * leetcode 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class KthLargestElement {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,5,6,4};
        int k = 2;
        int kthLargest = findKthLargest(nums, k);
        System.out.println("输出: " + kthLargest);
    }

    public static int findKthLargest(int[] nums, int k) {
        // 思路：利用优先队列PrioriQueue ,自定义插入顺序，来实现大顶堆（堆中只保留四个元素）
        // 参数为 lamda表达式，定义Comparator比较器，自定义插入顺序
        PriorityQueue<Integer> heap =  new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        for (int n: nums) {
            heap.add(n);
            if (heap.size() > k) heap.poll(); // 确保堆中保留四个元素
        }
        return heap.poll();
    }
}
