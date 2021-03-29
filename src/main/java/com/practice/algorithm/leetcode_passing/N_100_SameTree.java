package com.practice.algorithm.leetcode_passing;

/**
 * 100. 相同的树
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * 示例 1：
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：p = [1,2,1], q = [1,1,2]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * 两棵树上的节点数目都在范围 [0, 100] 内
 * -104 <= Node.val <= 104
 *
 * @author Jimmy
 * @version 1.0, 2021/03/29
 * @since practice 1.0.0
 */
public class N_100_SameTree {

    // dfs 判断没个节点是否相等。
    public boolean isSameTree(TreeNode p, TreeNode q) {
        boolean result = true;
        return dfs(p, q, result);
    }

    private boolean dfs(TreeNode p, TreeNode q, boolean result) {
        if (p == null && q == null) {
            return result;
        }

        if (p == null || q == null) {
            return false;
        }
        result = p.val == q.val;

        boolean left = dfs(p.left, q.left, result);
        boolean right = dfs(p.right, q.right, result);

        return result && left && right;
    }
}
