package com.practice.algorithm.leetcode_passing;

/**
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * <p>
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 * <p>
 * 提示：
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 *
 * @author Jimmy
 * @version 1.0, 2021/03/29
 * @since practice 1.0.0
 */
public class N_61_RotateList {

    // 思路：根据移动的情况来看，可以看作是一个环形列表，找合适的切割点。
    // 所以，第一步：构造环。
    //      第二步：找合适的切割点。
    public static ListNode rotateRight(ListNode head, int k) {
        ListNode resultNode = new ListNode(-1);
        if (head == null) {
            return resultNode.next;
        }
        ListNode curNode = head;
        int num = 1;
        while (curNode.next != null) {
            num++;
            curNode = curNode.next;
        }
        curNode.next = head;

        curNode = head;
        int index = 0;

        while (true) {
            if (index == num - (k % num + 1)) {
                resultNode.next = curNode.next;
                curNode.next = null;
                break;
            }
            index++;
            curNode = curNode.next;
        }
        return resultNode.next;
    }

    public static void main(String[] args) {
        ListNode resultNode1 = new ListNode(1);
        ListNode resultNode2 = new ListNode(2);
        ListNode resultNode3 = new ListNode(3);
        ListNode resultNode4 = new ListNode(4);
        ListNode resultNode5 = new ListNode(5);
        resultNode1.next = resultNode2;
        resultNode2.next = resultNode3;
        resultNode3.next = resultNode4;
        resultNode4.next = resultNode5;

        ListNode listNode = rotateRight(resultNode1, 2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
