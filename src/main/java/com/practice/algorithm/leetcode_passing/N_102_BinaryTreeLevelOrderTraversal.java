package com.practice.algorithm.leetcode_passing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层序遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 *
 * @author Jimmy
 * @version 1.0, 2021/03/16
 * @since practice 1.0.0
 */
public class N_102_BinaryTreeLevelOrderTraversal {

    // 解题思路: BFS 广度优先遍历 -> 就是按层遍历，一层一层的遍历。
    // 所以我们需要记录每层的根节点，以便我们遍历完一层之后，去遍历下一层。记录结果集的时候需要每遍历完一层就存储一层。
    // 存储根节点，需要满足FIFO（先进先出）原则。我们可以使用队列来保存（LinkedList就是一个队列）。
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList();
            int currentLevelSize = queue.size();

            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }
}
