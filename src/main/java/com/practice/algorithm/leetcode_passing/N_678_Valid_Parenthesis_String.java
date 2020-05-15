package com.practice.algorithm.leetcode_passing;

/**
 * @author Higmin
 * @date 2020/5/15 10:58
 *
 * Leetcode 678. 有效的括号字符串
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 *
 * 任何左括号 ( 必须有相应的右括号 )。
 * 任何右括号 ) 必须有相应的左括号 ( 。
 * 左括号 ( 必须在对应的右括号之前 )。
 * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 * 示例 1:
 *
 * 输入: "()"
 * 输出: True
 * 示例 2:
 *
 * 输入: "(*)"
 * 输出: True
 * 示例 3:
 *
 * 输入: "(*))"
 * 输出: True
 * 注意:
 *
 * 字符串大小将在 [1，100] 范围内。
 **/
public class N_678_Valid_Parenthesis_String {

	public static void main(String[] args) {
		String s1 = "()";
		String s2 = ")(";
		String s3 = "*)(*";
		System.out.println("输入：" + s1 + " 结果：" + checkValidString(s1));
		System.out.println("输入：" + s2 + " 结果：" + checkValidString(s2));
		System.out.println("输入：" + s3 + " 结果：" + checkValidString(s3));
	}

	public static boolean checkValidString(String s) {
		int min = 0, max = 0; // 维护当前左括号的数量范围：[min, max]
		for (int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if (c == '('){
				++min;
				++max;
			} else if (c == ')'){
				if (min > 0) min--; // 抵消一个左括号
				if (max-- == 0) return false; // 左括号不够
			} else {
				if (min > 0) min--;  // 可作为右括号，抵消一个左括号
				++max; // 可作为左括号
			}
		}
		return min == 0;
	}
}
