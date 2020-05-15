package com.practice.algorithm.leetcode_passing;

import java.util.Arrays;

/**
 * @author Higmin
 * @date 2020/5/15 15:50
 *
 * leetcode：88. 合并两个有序数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 *
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 **/
public class N_88_Merge_Sorted_Array {

	/**
	 * 思路：合并两个有序数组，注意"有序"这个关键点
	 * 我们可以采用双指针 实现两个数组一次遍历，来比较两个数组的当前元素，然后把小（大）的那个数，放在新的数组中，这样就实现了最优时间复杂度 O（m+n）
	 * 当一个数组遍历结束后，把另一个数组剩余部分添加到结果的尾部
	 *
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static int[] merge(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		int[] result = new int[m + n];
		int p1 = 0;
		int p2 = 0;
		int pRes = 0;
		// 比较元素并放入到结果数组中
		while ((p1 < m) && (p2 < n)) {
			result[pRes++] =  nums1[p1] < nums2[p2] ? nums1[p1++] : nums2[p2++];
		}
		// 当一个数组遍历结束后，把另一个数组剩余部分添加到结果的尾部
		// public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
		// 参数解释:
		// Object src : 要拷贝的原数组
		// int srcPos : 原数组需要拷贝的起始位置
		// Object dest : 目标数组
		// int destPos : 目标数组的开始起始位置
		// int length  : 要copy的数组的长度
		if (p1 < m) System.arraycopy(nums1, p1, result, p1 + p2, (m + n) - (p1 + p2));
		if (p2 < m) System.arraycopy(nums2, p2, result, p1 + p2, (m + n) - (p1 + p2));

		return result;
	}

	public static void main(String[] args) {
		int[] nums1 = {1,3,5,7,9};
		int[] nums2 = {2,4,6,8,10};
		System.out.println(Arrays.toString(merge(nums1, nums2)));
	}
}
