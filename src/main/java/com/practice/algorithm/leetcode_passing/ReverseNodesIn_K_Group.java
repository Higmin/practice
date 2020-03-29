package com.practice.algorithm.leetcode_passing;

/**
 * @author Higmin
 * @date 2020/3/27 16:03
 *
 * leetcode 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 *
 * 说明：
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 **/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class ReverseNodesIn_K_Group {

	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(4);
		ListNode l4 = new ListNode(5);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		ListNode listNode = reverseKGroup(l1, 2);
		while (listNode != null) {
			System.out.println(listNode.val);
			listNode = listNode.next;
		}
	}
	public static ListNode reverseKGroup(ListNode head, int k) {
		ListNode dummy = new ListNode(-1); // 创建一个假头节点，防止指针移动找不到真正的头节点
		dummy.next = head;
		ListNode pre = dummy;
		ListNode end = dummy;
		while (end.next != null){
			for (int i = 0; i < k && end != null; i++) end = end.next;
			if (end == null) break;
			ListNode start = pre.next;
			ListNode next = end.next;
			end.next = null;
			pre.next = reverse(start);
			start.next = next;
			pre = start;
			end = pre;
		}
		return dummy.next;
	}

	public static ListNode reverse(ListNode l){
		ListNode pre = null;
		ListNode cur = l;
		while(cur != null){
			ListNode temp = cur.next;
			cur.next = pre;
			pre = cur;
			cur = temp;
		}
		return pre;
	}
}
