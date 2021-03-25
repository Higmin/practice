package com.practice.algorithm.leetcode_passing;

/**
 * 82. 删除排序链表中的重复元素 II
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 * 返回同样按升序排列的结果链表。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * <p>
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 * <p>
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 *
 * @author Jimmy
 * @version 1.0, 2021/03/25
 * @since practice 1.0.0
 */
public class N_82_RemoveDuplicatesFromSortedList_II {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre_resultHead = new ListNode(-1);
        pre_resultHead.next = head;

        ListNode pre = pre_resultHead; // 由于 pre 节点会在遍历的时候向后移动，所以这里使用 pre 的引用来代替。防止 pre 移动之后 pre.next 不是头结点。
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                ListNode move = cur.next;
                while (move != null && move.next != null && cur.val == move.next.val) {
                    move = move.next;
                }
                pre.next = move.next;
                cur = move.next;
            } else {
                pre = pre.next;
                cur = cur.next;
            }
        }
        return pre_resultHead.next;
    }
}
