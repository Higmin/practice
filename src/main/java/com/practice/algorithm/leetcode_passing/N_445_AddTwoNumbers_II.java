package com.practice.algorithm.leetcode_passing;

import java.util.Stack;

/**
 * 445. 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 进阶：
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 * 示例：
 * <p>
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 *
 * @author Jimmy
 * @version 1.0, 2021/03/24
 * @since practice 1.0.0
 */
public class N_445_AddTwoNumbers_II {

    // 思路：通常遍历列表或者数组，习惯从前向后遍历。
    // 但是题目中要做加法的话，是从低位想高位不断相加，也就是需要从后先前，那我们可以考虑使用栈来存储一下。以实现逆序。
    // 按位不断相加，需要考虑进位；另外如果num1 和 num2 位数不相同的时候，缺省的位数用 0 来替代即可。
    // 由于是按位相加的，所以要在循环之后后判断 是否还有进位，有的话要在最高位补上进位值。

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();

        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }

        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int num1 = stack1.isEmpty() ? 0 : stack1.pop().val;
            int num2 = stack2.isEmpty() ? 0 : stack2.pop().val;
            int sum = (num1 + num2 + carry) % 10;
            carry = (num1 + num2 + carry) / 10;
            ListNode tempNode = new ListNode(sum);
            tempNode.next = result.next;
            result.next = tempNode;
        }

        if (carry != 0) {
            ListNode tempNode = new ListNode(carry);
            tempNode.next = result.next;
            result.next = tempNode;
            carry = 0;
        }
        return result.next;
    }
}
