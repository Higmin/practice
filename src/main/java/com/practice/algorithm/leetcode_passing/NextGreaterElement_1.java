package com.practice.algorithm.leetcode_passing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author Higmin
 * @date 2020/3/29 22:46
 *
 * leecode 496. 下一个更大元素 I
 * 给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 *
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出-1。
 *
 * 示例 1:
 *
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 *     对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 *     对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 *     对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
 * 示例 2:
 *
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于num1中的数字2，第二个数组中的下一个较大数字是3。
 *     对于num1中的数字4，第二个数组中没有下一个更大的数字，因此输出 -1。
 * 注意:
 *
 * nums1和nums2中所有元素是唯一的。
 * nums1和nums2 的数组大小都不超过1000。
 */
public class NextGreaterElement_1 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{4,1,2};
        int[] nums2 = new int[]{1,3,4,2};
        System.out.println(Arrays.toString(nextGreaterElement1(nums1, nums2)));
    }

    /**
     * 思路：找出nums2中每个元素的下一个更大元素，并放在map中。然后用nums1中的每个元素去取结果值即可
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums2.length; i++){ // 循环nums2中的每一个元素
            int num2 = nums2[i];
            int cur = i;
            while(cur < nums2.length){ // 从当前元素的下一个开始找 比当前元素更大的第一个元素。
                if(num2 < nums2[cur]) { // 如果找到则此次结束寻找，开始找下一个元素的比它更大的第一个更大元素。
                    map.put(num2,nums2[cur]);
                    break;
                }
                else if(cur == nums2.length-1) map.put(num2, -1); // 如果没找到，则标记 -1
                cur++;
            }
        }

        int[] res = new int[nums1.length]; // 初始化返回值数组
        for(int j = 0; j <nums1.length; j++){ // 将nums1对应元素的结果写到返回值数组中
            res[j] = map.get(nums1[j]);
        }
        return res;
    }
}
