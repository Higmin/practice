package com.practice.algorithm.leetcode_passing;

/**
 *
 * @author Higmin
 * @date 2020/4/06 18:22
 *
 * leetcode 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class N_160_IntersectionOfTwoLinkedLists {

    public static void main(String[] args) {
        ListNode l11 = new ListNode(3);
        ListNode l12 = new ListNode(1);
        ListNode l13 = new ListNode(8);
        ListNode l14 = new ListNode(4);
        ListNode l15 = new ListNode(5);
        l11.next = l12;
        l12.next = l13;
        l13.next = l14;
        l14.next = l15; // 链表A: 3 -> 1 -> 8 -> 4 -> 5

        ListNode l21 = new ListNode(7);
        l21.next = l13; // 链表B: 7 -> 8 -> 4 -> 5
        ListNode node = getIntersectionNode(l11, l21);
        System.out.println(node.val);
    }

// 思路：题目中相交链表的特点是两个链表相交之后的部分全部相同
// 所以怎么找到公共部分才是我们的目的。我们可以这么做：
// 两个链表A和B，如果相交之后的部分都相同，那么A+B和B+A最后的公共部分就是相交之后的部分
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode curA = headA;
        ListNode curB = headB;
        while(curA != curB) {
            if (curA == null) curA = headB;
            else curA = curA.next;
            if (curB == null) curB = headA;
            else curB = curB.next;
        }
        return curA;
    }
}
