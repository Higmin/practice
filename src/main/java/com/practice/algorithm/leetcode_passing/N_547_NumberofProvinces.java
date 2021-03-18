package com.practice.algorithm.leetcode_passing;

/**
 * <p>
 * 示例 1：
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 * <p>
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
