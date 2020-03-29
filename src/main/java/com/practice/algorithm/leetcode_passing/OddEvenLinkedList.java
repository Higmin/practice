package com.practice.algorithm.leetcode_passing;

/**
 *
 * @author Higmin
 * @date 2020/3/29 20:01
 *
 * leetcode 328. 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 *
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 *
 * 说明:
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        // 思路： 构建两个链表分别存放奇数节点和偶数节点，遍历完之后将两个链表连接起来。
        if(head == null)  return null;
        // 首先分别初始化两个链表的头节点。并给偶数链表固定一个头节点，防止指针移动找不到真正的头节点。
        ListNode head1 = head, head2 = head.next, dummy2 = head2;
        while(head2 != null && head2.next != null){
            // 给第一个链表添加节点
            head1.next = head2.next;
            head1 = head1.next;
            // 给第二个链表添加节点
            head2.next = head1.next;
            head2 = head2.next;
        }
        head1.next = dummy2; // 连接两个链表
        return head;
    }
}
