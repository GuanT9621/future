package leetcode;

/**
 * https://leetcode-cn.com/problems/word-search/
 * 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 思路
 * 1 遍历网格，找到一个开头元素一样的
 * 2 标记该位置，并以他为中心，匹配上下左右；如果有，则迭代标记；如果没有，则返回false；
 * 3 终止，当 word 全部匹配后返回 true
 */
public class N79_m {
    // 右 左 下 上
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean exist(char[][] board, String word) {
        int h = board.length;
        int w = board[0].length;
        int mark = 0;
        boolean[][] visited = new boolean[h][w];
        for (int i=0; i < h; i++) {
            for (int j=0; j < w; j++) {
                boolean match = check(board, visited, i, j, word, mark);
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)) {
            return false;
        } else if (k == s.length() - 1) { // 匹配完了word
            return true;
        }
        // 防止倒回，如同 AAAA 格式
        visited[i][j] = true;
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0];
            int newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }

}
