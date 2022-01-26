package leetcode;

/**
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 思路：动态规划
 * 状态转移方程：
 * 当 i>0 且 j=0 时，dp[i][0] = dp[i-1][0]+grid[i][0]。
 * 当 i=0 且 j>0 时，dp[0][j] = dp[0][j-1]+grid[0][j]。
 * 当 i>0 且 j>0 时，dp[i][j] = min(dp[i-1][j], dp[i][j-1])+grid[i][j]。
 *
 * 最后得到 dp[m-1][n-1] 的值即为从网格左上角到网格右下角的最小路径和
 */
public class N64_m {

    public static int minPathSum(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        for (int i=1; i<rows; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int j=1; j<columns; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        for (int i=1; i<rows; i++) {
            for (int j=1; j<columns; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[rows-1][columns-1];
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {{1,3,1}, {1,5,1}, {4,2,1}};
        int i = minPathSum(grid);
        System.out.println(i);
    }

}
