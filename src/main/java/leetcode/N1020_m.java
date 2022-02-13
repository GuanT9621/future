package leetcode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/number-of-enclaves/
 * 飞地的数量
 * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 *
 * 思路
 * 本题考察的是遍历方式 dfs bfs
 *
 */
public class N1020_m {
    public static int[][] moves = new int[][]{{0, 1},{0, -1},{-1, 0},{1, 0}};
    private static int m, n;
    private static boolean[][] visited; // 保存可到达的坐标

    public static int numEnclaves(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) { // 从边界上开始统计能达到的坐标
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);
        }
        for (int j = 1; j < n - 1; j++) { // 从边界上开始统计能达到的坐标
            dfs(grid, 0, j);
            dfs(grid, m - 1, j);
        }
        int ans = 0; // 对比 grid 和 visited 得到不能到达的坐标个数
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    ans++;
                }
            }
        }
        return ans;
    }
    public static void dfs(int[][] grid, int row, int col) {
        if (row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == 0 || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        for (int[] move : moves) {
            dfs(grid, row + move[0], col + move[1]);
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
        int i = numEnclaves(grid);
        System.out.println(i);
    }
}
