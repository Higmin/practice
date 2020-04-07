package com.practice.algorithm.leetcode_passing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Higmin
 * @date 2020/3/30 21:05
 *
 * leetcode 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class N_15_ThreeNumSum {

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums).toString());
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        // 思路：先排序，然后依次固定一个数（for循环），同时加两个指针，一个在前一个在后，不断移动指针去找结果
        // 注意去重如下：
        // 一次是在固定数之后，和上一次固定的数比较是否相同；
        // 另一次是在固定好一个数，找到唯一解时，（因为第二个数也相同的话，那么第三个数一定相同，会造成重复解）
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length < 3) return res;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0) return res; // 如果最小的数>0，则不可能相加为0
            // 定义两个指针，指向另外两个元素，一个从小到大，一个从大到小
            int left = i + 1, right= nums.length -1;
            if(i > 0 && nums[i] == nums[i-1]) continue; // 第一次去重,跳出本次循环，注意不要写成break
            while(left < right){ // 寻找相加等于0
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0){ // 如果相加等于0，则找到一组答案，加到结果集合中，然后移动左右指针
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //第二次去重：找到这两个数之后， 那么这两个数需要去重
                    while(left < right && nums[left] == nums[left+1]) left++;
                    while(left < right && nums[right] == nums[right-1]) right--;
                    left++;
                    right--;
                }
                // 如果没找到，判断结果的值大于0还是小于0
                if(sum > 0) right--; // 大于0的话：右指针--（减小数值）
                if(sum < 0) left++; // 小于0的话：左指针++(增加数值)
            }

        }
        return res;
    }
}
