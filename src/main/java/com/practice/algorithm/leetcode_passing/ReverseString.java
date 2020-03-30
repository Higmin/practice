package com.practice.algorithm.leetcode_passing;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author Higmin
 * @date 2020/3/30 9:36
 *
 * leetcode 344. 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 *
 * 示例 1：
 *
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 *
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 **/
public class ReverseString {

	public static void main(String[] args) {
		char[] s = new char[]{'a','b','c','d','e'};
		System.out.println(Arrays.toString(reverseString(s)));
	}

	public static char[] reverseString(char[] s) {
		// 思路：首先想到的是利用栈来实现字符串反转，但是不满足题目要求，我们再想一下：字符串反转的话，就是把头变成尾，第二位变成倒数第二位.....
		// 所以我们可以利用 对称位置的元素 互换来实现
		int left = 0, right = s.length - 1;
		while(left < right){
			char temp = s[right];
			s[right] = s[left];
			s[left] = temp;
			right--;
			left++;
		}
		return s;
	}
}
