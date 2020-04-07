package com.practice.algorithm.leetcode_passing;

/**
 * @author Higmin
 * @date 2020/3/27 17:13
 *
 * leetcode 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 进阶:如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 **/
public class N_53_MaximumSubarray {

	public static void main(String[] args) {
		int[] arr= {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(maxSubArray(arr));
	}

	public static int maxSubArray(int[] nums) {
		// 核心思想：我们需要的是求和最大的连续子数组，所以遍历数组,以最大和sum为出发点出发
		// 如果sum > 0 : sum对结果有增益效果，那么sum += num;
		// 如果sum < 0 : sum对结果没有增益效果，则重置sum = num;
		int sum = 0;
		if(nums == null || nums.length < 1) return 0;
		int ans = nums[0]; // 结果值
		for(int num : nums){
			if(sum > 0){ // 有增益效果
				sum += num;
			}else {
				sum = num;
			}
			ans = Math.max(ans, sum);
		}
		return ans;
	}
}
