package com.practice.algorithm.leetcode_passing;

/**
 *
 * @author Higmin
 * @date 2020/4/01 22:10
 *
 * leetcode 23. 合并K个排序链表
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class MergeKSortedLists {

    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(5);
        l11.next = l12;
        l12.next = l13;

        ListNode l21 = new ListNode(1);
        ListNode l22 = new ListNode(3);
        ListNode l23 = new ListNode(4);
        l21.next = l22;
        l22.next = l23;

        ListNode l31 = new ListNode(2);
        ListNode l32 = new ListNode(6);
        l31.next = l32;
        ListNode[] lists =  new ListNode[]{l11, l21, l31};

        ListNode listNode = mergeKLists(lists);

        assert listNode != null;
        while(listNode.next != null) {
            System.out.print(listNode.val + " -> ");
            listNode = listNode.next;
        }
    }

    // 思路：采用分治法（不断拆分到最小，然后进行相邻的两个链表进行 两两合并）
    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length ==0){
            return null;
        }
        return mergeSort(lists, 0, lists.length - 1);
    }

    /**
     *  通过mid将数组一分为二，并不断缩小规模，当规模为1时返回并开始合并
     * 	通过合并两个链表，不断增大其规模，整体看就是不断缩小-最后不断扩大的过程
     * @param lists
     * @param start
     * @param end
     * @return
     */
    public static ListNode mergeSort(ListNode[] lists, int start, int end){
        if(start == end){
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode left = mergeSort(lists, start, mid);
        ListNode right = mergeSort(lists, mid + 1, end);
        return merge(left, right);
    }

    /**
     * 合并两个有序链表  ==》 方法1 双指针
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode merge(ListNode l1, ListNode l2) {
        // 思路：我们创建一个新的链表，通过比较两个有序链表的元素，新链表的指针不断指向正确的元素，结果将这个新的链表返回
        ListNode dummy = new ListNode(-1); // 创建一个假的头节点，防止指针移动找不到真正的列表头。
        ListNode prev = dummy; // 新链表的前驱指针，用于放入新的元素并不断向后移动
        while(l1 != null && l2 != null){
            if (l1.val <= l2.val){
                prev.next = l1;
                l1 = l1.next;
            }else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = (l1 == null) ? l2 : l1; // 如果两个链表不一样长，剩余部分都加到新链表中
        return dummy.next;
    }


    /**
     * 合并两个有序链表 ==》 方法2 递归
     * @param left
     * @param right
     * @return
     */
    public static ListNode merge2(ListNode left, ListNode right){
        if(left == null || right == null){
            return left == null ? right : left;
        }
        if(left.val < right.val) {
            left.next = merge(left.next, right);
            return left;
        }else{
            right.next = merge(left, right.next);
            return right;
        }
    }
}
