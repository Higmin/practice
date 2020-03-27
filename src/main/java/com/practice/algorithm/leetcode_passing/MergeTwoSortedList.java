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
	// 思路：有序链表合并，可以利用链表的指针进行比较操作，我们要判断 l1 和 l2 哪一个的头元素更小，然后递归地决定下一个添加到结果里的值。
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) return l2;
		else if (l2 == null) return l1;
		else if (l1.val < l2.val){
			l1.next = mergeTwoLists(l1.next,l2);
			return l1;
		}else{ // l1.val >= l2.val
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}
}
