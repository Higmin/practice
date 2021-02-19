package com.practice.algorithm.leetcode_passing;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 *
 * @author Jimmy
 * @version 1.0, 2021/02/19
 * @since practice 1.0.0
 */
public class N_3_LongestSubstringWithoutRepeatingCharacters {

    // 审题发现：需要的是“不重复”的“最长” 的 “子串”。 可以利用 双指针 实现滑动窗口 解决。
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 定义滑动窗口的两个指针的起始位置。
        int res = 0;
        int begin = 0, end = 0;
        Set<Character> set = new HashSet<>();
        while (begin < s.length() && end < s.length()) {
            char c = s.charAt(end);
            if (!set.contains(c)) {
                set.add(c);
                end++;
            } else {
                set.remove(s.charAt(begin++));
            }
            res = Math.max(res, end - begin);
        }
        return res;
    }
}
