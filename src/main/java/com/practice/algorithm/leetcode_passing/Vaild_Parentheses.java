package com.practice.algorithm.leetcode_passing;

import java.util.Stack;

/**
 * @author Higmin
 * @date 2020/3/27 14:09
 *
 * leetcode 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 **/
public class Vaild_Parentheses {

	public boolean isValid(String s) {
		if ("".equals(s)) return true;
		if (s == null || s.length() < 2) return false;
		Stack stack = new Stack();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			// 如果是左括号就压入栈中
			if (c == '(' || c == '[' || c == '{') {
				stack.push(c);
			} else if (c == ')' || c == ']' || c == '}'){ //  建议写算法的时候，不要写else,都写成else if ,顺便把大括号也写上，最后再做优化。
				//如果是右括号，则需要考虑两种情况：一种是匹配到对应的括号，则出栈，另一种是没有匹配到，需要重点考虑。
				if (c == ')'){
					if (stack.isEmpty() || (char)stack.peek() != '(') return false; // 如果右括号匹配不到对应的左括号，返回false, 否则能匹配到就出栈
				}else if (c == ']'){
					if (stack.isEmpty() || (char)stack.peek() != '[') return false; // 如果右括号匹配不到对应的左括号，返回false, 否则能匹配到就出栈
				}else if (c == '}'){
					if (stack.isEmpty() || (char)stack.peek() != '{') return false; // 如果右括号匹配不到对应的左括号，返回false, 否则能匹配到就出栈
				}
				stack.pop();
			}
		}
		if (stack.empty()) return true;
		return false;
	}
}
