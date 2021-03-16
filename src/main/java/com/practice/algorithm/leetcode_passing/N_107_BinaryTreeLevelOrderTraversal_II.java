package com.practice.algorithm.leetcode_passing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 107. 二叉树的层序遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其自底向上的层序遍历为：
 * <p>
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 *
 * @author Jimmy
 * @version 1.0, 2021/03/16
 * @since practice 1.0.0
 */
public class N_107_BinaryTreeLevelOrderTraversal_II {

    // 此题的解法类似 N——102 二叉树的BFS（广度优先遍历）遍历。
    // 区别在于此题需要自底向上的层序遍历。
    // 那我们可以可以选择利用 LinkedList 的排序特点来存储每一层的结果。每一层都插入到最前面。
    // LinkedList 可以根据插入先后和最近访问数据来排序。
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList();
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.addFirst(level);
        }
        return result;
    }
}
