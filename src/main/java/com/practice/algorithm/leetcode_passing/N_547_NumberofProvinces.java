package com.practice.algorithm.leetcode_passing;

/**
 * 547. 省份数量
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 *
 * 返回矩阵中 省份 的数量。
 *
 *
 * 示例 1：
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 *
 * 示例 2：
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 *
 * 提示：
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 *
 * @author Jimmy
 * @version 1.0, 2021/03/18
 * @since practice 1.0.0
 */
public class N_547_NumberofProvinces {

    // [
    //      [1,1,0],
    //      [1,1,0],
    //      [0,0,1]
    // ]

    // 把 nn 个城市和它们之间的相连关系看成图，城市是图中的节点，相连关系是图中的边，
    // 给定的矩阵 即为图的邻接矩阵，省份即为图中的连通分量。
    // 那么利用图的邻接矩阵 的 深度优先搜索或广度优先搜索即可实现。（也可以通过并查集实现。）
    public static int findCircleNum(int[][] isConnected) {
        int result = 0;
        int length = isConnected.length;
        boolean[] visited = new boolean[length];
        for (int i = 0; i < length; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                result++;
            }
        }
        return result;
    }

    public static void dfs(int[][] isConnected, boolean[] visited, int i) {
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                // 需要找出两个节点相邻的所有子节点，所以需要从反向迭代。
                dfs(isConnected, visited, j);
            }
        }
    }

    public static void main(String[] args) {
        // int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        int[][] isConnected = {{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};

        System.out.println(findCircleNum(isConnected));
    }
}
