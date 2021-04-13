package com.practice.algorithm.leetcode_passing;

/**
 * 783. 二叉搜索树节点最小距离
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * <p>
 * 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [2, 100] 内
 * 0 <= Node.val <= 105
 *
 * @author Jimmy
 * @version 1.0, 2021/04/13
 * @since practice 1.0.0
 */
public class N_783_MinimumDistanceBetweenBSTNodes {

    // 二叉搜索树中序遍历得到的值序列是递增有序的
    // 所以此题可以采用二叉树的中序遍历来实现。

    private int pre = -1;
    private int ans = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            ans = Math.min(ans, root.val - pre);
            pre = root.val;
        }
        dfs(root.right);
    }
}
