package com.practice.algorithm.leetcode_passing;

/**
 * @author Higmin
 * @date 2020/3/27 14:23
 *
 * leetcode 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 **/

/** Definition for singly-linked list. */
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}

public class MergeTwoSortedList {

	public static void main(String[] args) {
		ListNode l11 = new ListNode(1);
		ListNode l12 = new ListNode(2);
		ListNode l13 = new ListNode(4);
		l11.next = l12;
		l12.next = l13;

		ListNode l21 = new ListNode(1);
		ListNode l22 = new ListNode(3);
		ListNode l23 = new ListNode(4);
		l21.next = l22;
		l22.next = l23;

		ListNode listNode = mergeTwoLists(l11, l21);
		while (listNode != null){
			System.out.println(listNode.val);
			listNode = listNode.next;
		}

	}
	// 思路：我们创建一个新的链表，通过比较两个有序链表的元素，新链表的指针不断指向正确的元素，结果将这个新的链表返回
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1); // 创建一个假的头节点，防止指针移动找不到真正的列表头。
		ListNode pre = dummy; // 新链表的前驱指针，用于放入新的元素并不断向后移动
		while(l1 != null && l2 != null){
			if (l1.val <= l2.val){
				pre.next = l1;
				l1 = l1.next;
			}else {
				pre.next = l2;
				l2 = l2.next;
			}
			pre = pre.next;
		}
		pre.next = (l1 == null) ? l2 : l1; // 如果两个链表不一样长，剩余部分都加到新链表中
		return dummy.next;
	}
}
