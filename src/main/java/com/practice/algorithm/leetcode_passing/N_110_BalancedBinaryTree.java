package com.practice.algorithm.leetcode_passing;

import sun.reflect.generics.tree.Tree;

/**
 * @author Higmin
 * @date 2020/4/7 10:16
 *
 * leetcode 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 **/
public class N_110_BalancedBinaryTree {
	/**
	 * 思路：这是一道树的高度(leetcode_104)的变形题，我们需要算出树的高度，以便后来做左子树和右子树高度的比较。
	 *
	 * 返回 true 的约束条件有三个：
	 * 1.左右子树高度差的绝对值 <=1
	 * 2.每一棵左子树都是平衡树
	 * 3.每一棵右子树都是平衡树
	 * @param root
	 * @return
	 */
	public boolean isBalanced(TreeNode root) {
		if (root == null) return true;
		return Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1 // 1.左右子树高度差的绝对值 <=1
				&& isBalanced(root.left) // 2.每一棵左子树都是平衡树
				&& isBalanced(root.right); // 3.每一棵右子树都是平衡树
	}

	/**
	 * 返回树的高度，此处为了简便使用递归方式，也可以采用深度优先遍历来实现，具体参考 leetcode-104,本项目中也有该题目的实现。
	 * @param root
	 * @return
	 */
	public int getHeight(TreeNode root){
		if (root == null) return 0;
		int leftHeigth = getHeight(root.left) + 1;
		int rightHeight = getHeight(root.right) + 1;
		return Math.max(leftHeigth, rightHeight);
	}
}
