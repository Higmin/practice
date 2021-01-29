package com.practice.algorithm.leetcode_passing;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的前序遍历
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[1,2]
 *
 * 示例 5：
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *
 * 提示：
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * @author Jimmy
 * @version 1.0, 2021/01/29
 * @since practice 1.0.0
 */
public class N_144_BinaryTreePreorderTraversal {

    // 前序遍历： 根 --> 左 --> 右
    // 关键点：以"根"节点为考虑中心，出栈时间以"根"节点为主，出栈同时不断入栈当前"根"节点的右节点和左节点。
    // 每次弹出栈都是弹出 “根” ，同时需要按 “右节点” 和 “左节点” 的顺序将其压入栈中。
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>(); // 先初始化返回值类型。

        if (root == null) {
            return resultList; // 判断是不是一颗空的树。
        }

        // 采用压栈的方式去实现先序遍历。
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currNode = root;

        stack.push(currNode); // 前序遍历先压入根，因为循环第一步需要从根取出来开始。

        while (!stack.isEmpty()) {
            currNode = stack.pop();
            resultList.add(currNode.val);

            if (currNode.right != null) {
                stack.push(currNode.right);
            }

            if (currNode.left != null) {
                stack.push(currNode.left);
            }
        }
        return resultList;
    }
}
