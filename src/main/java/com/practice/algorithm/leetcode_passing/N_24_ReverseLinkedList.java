package com.practice.algorithm.leetcode_passing;

/**
 * 206. 反转单链表
 * <p>
 * 反转一个单链表。
 * <p>
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * @author Jimmy
 * @version 1.0, 2021/02/19
 * @since practice 1.0.0
 */
public class N_24_ReverseLinkedList {

    // 思路： 反转每一个指针的指向
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
