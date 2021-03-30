package com.practice.algorithm.leetcode_passing;

/**
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 * <p>
 * <p>
 * 提示：
 * <p>
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 * @author Jimmy
 * @version 1.0, 2021/03/30
 * @since practice 1.0.0
 */
public class N_79_WordSearch {

    // 使用图的深度优先遍历 + 回溯。
    // 要注意回溯的时机。
    public static boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visit = new boolean[h][w];

        if (word.length() == 1 && h == 1 && w == 1 && board[0][0] == word.charAt(0)) {
            return true;
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean result = dfs(board, word, 0, i, j, visit);
                if (result) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean dfs(char[][] board, String word, int index, int i, int j, boolean[][] visit) {
        if (index == word.length()) {
            return true;
        }

        if (board[i][j] != word.charAt(index) || visit[i][j]) {
            return false;
        }

        visit[i][j] = true; // todo 将路径设为访问过。

        boolean top = false, down = false, left = false, right = false;
        if (i - 1 >= 0) {
            int temp = i;
            i--;
            top = dfs(board, word, index + 1, i, j, visit);
            if (!top) {
                i = temp;
            }
        }

        if (i + 1 < board.length) {
            int temp = i;
            i++;
            down = dfs(board, word, index + 1, i, j, visit);
            if (!down) {
                i = temp;
            }
        }

        if (j - 1 >= 0) {
            int temp = j;
            j--;
            left = dfs(board, word, index + 1, i, j, visit);
            if (!left) {
                j = temp;
            }
        }

        if (j + 1 < board[0].length) {
            int temp = j;
            j++;
            right = dfs(board, word, index + 1, i, j, visit);
            if (!right) {
                j = temp;
            }
        }

        visit[i][j] = false; // todo 这一步回溯很关键 ：没有停止的话，需要将访问过的路径设为 未访问过。

        return top || down || left || right;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        char[][] board2 = {{'a'}};

        System.out.println(exist(board, "ABCCED"));
        System.out.println(exist(board, "SEE"));
        System.out.println(exist(board, "ABCB"));
        System.out.println(exist(board2, "a"));
        System.out.println(exist(board2, "ab"));
    }
}
