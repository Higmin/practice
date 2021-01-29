package com.practice.algorithm.leetcode_passing;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * The xxx class for xxx.
 *
 * @author Higmin
 * @version 1.0, 2021/01/28
 * @since practice 1.0.0
 * <p>
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 * <p>
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 * <p>
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[2,1]
 * <p>
 * 示例 5：
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *  
 * 提示：
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 */
public class N_94_BinaryTreeInorderTraversal {

    // 中序遍历：左 -> 根 -> 右
    // 关键点：向“左”考虑，出栈时间左节点为主，我们需要将左节点不断压入栈，当没有左节点的时候才能出栈，出栈的时候同理依次判断下一个右节点。
    // 需要注意的是：
    // 将左(右)节点不断入栈的过程，已经将跟"根"节点看作左(右)节点做了处理，只需要在出栈的时候将右节点作为当前节点，依次判断。

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>(); // 先初始化返回值类型。

        if (root == null) { // 判断是不是一颗空的树。
            return resultList;
        }

        // 采用压栈的方式去实现中序遍历。
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currNode = root;
        while (currNode != null || !stack.isEmpty()) {  // 循环条件： 1.因为外层循环是判断当前节点是否存在； 2.因为需要判断 前栈是否为空，要不断的弹出栈，所以栈为空的时候，就是循环结束的时候。
            while (currNode != null) {
                stack.push(currNode);
                currNode = currNode.left;
            }
            currNode = stack.peek();
            resultList.add(currNode.val);
            currNode = currNode.right;
        }
        return resultList;
    }
}