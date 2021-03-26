package com.practice.algorithm.leetcode_passing;

/**
 * 112. 路径总和
 * <p>
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：false
 * <p>
 * 提示：
 * 树中节点的数目在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 * @author Jimmy
 * @version 1.0, 2021/03/26
 * @since practice 1.0.0
 */
public class N_112_PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        boolean result = false;
        if (root == null) {
            return result;
        }
        return dfs(root, targetSum);
    }

    private boolean dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        int res = targetSum - root.val;
        if (res == 0 && root.left == null && root.right == null) {
            return true;
        } else {
            return dfs(root.left, res) || dfs(root.right, res);
        }
    }
}
