package leetcode;

/**
 * https://leetcode.cn/problems/cherry-pickup/
 * 一个N x N的网格(grid) 代表了一块樱桃地，每个格子由以下三种数字的一种来表示：
 *
 * 0 表示这个格子是空的，所以你可以穿过它。
 * 1 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。
 * -1 表示这个格子里有荆棘，挡着你的路。
 * 你的任务是在遵守下列规则的情况下，尽可能的摘到最多樱桃：
 *
 * 从位置 (0, 0) 出发，最后到达 (N-1, N-1) ，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为0或者1的格子）；
 * 当到达 (N-1, N-1) 后，你要继续走，直到返回到 (0, 0) ，只能向上或向左走，并且只能穿越有效的格子；
 * 当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为0）；
 * 如果在 (0, 0) 和 (N-1, N-1) 之间不存在一条可经过的路径，则没有任何一个樱桃能被摘到。
 *
 * 思路 动态规划
 * 采用两人同时从起点出发，每次计算两人当前全部樱桃的最优解。
 * 状态转移方程 ： F(x, y) = grid[x][y] + Max(F(x-1, y), F(x, y-1))
 * 边界 ： row || col < 0 和 grid[r][c] == -1 和  row && col == 0
 *
 * 不能采用一来一回的方法进行计算最优解，因为来回各自的最优解之和不一定为总体的最优解。
 * 比如下面的例子
 * {1,1,1,1,0,0,0},
 * {0,0,0,1,0,0,0},
 * {0,0,0,1,0,0,1},
 * {1,0,0,1,0,0,0},
 * {0,0,0,1,0,0,0},
 * {0,0,0,1,0,0,0},
 * {0,0,0,1,1,1,1},
 *
 */
public class N741_h {

    class Solution {
        boolean over = false;

        public int cherryPickup(int[][] grid) {
            int n = grid.length;
            int ans = subAns(grid, n - 1, n - 1, n - 1, n - 1);
            return over ? ans : 0;
        }

        private int subAns(int[][] grid, int row1, int col1, int row2, int col2) {
            // over end
            if (row1 < 0 || col1 < 0 || row2 < 0 || col2 < 0) {
                return -1;
            }
            if (grid[row1][col1] == -1 || grid[row2][col2] == -1) {
                return -1;
            }
            if (row1 == 0 && col1 == 0 && row2 == 0 && col2 == 0) {
                over = true;
                return grid[row1][col1];
            }
            int cur = row1 == row2 && col1 == col2 ? grid[row1][col1] : grid[row1][col1] + grid[row2][col2];
            int a = subAns(grid, row1 - 1, col1, row2 - 1, col2);
            int b = subAns(grid, row1, col1 - 1, row2, col2 - 1);
            int c = subAns(grid, row1 - 1, col1, row2, col2 - 1);
            int d = subAns(grid, row1, col1 - 1, row2 - 1, col2);
            int max = Math.max(a, Math.max(b, Math.max(c, d)));
            return cur + max;
        }
    }

    /** 超时优化 */
    class Solution2 {
        boolean over = false;

        public int cherryPickup(int[][] grid) {
            int n = grid.length;
            int ans = subAns(grid, n - 1, n - 1, n - 1, n - 1);
            return over ? ans : 0;
        }

        private int subAns(int[][] grid, int row1, int col1, int row2, int col2) {
            // over end
            if (row1 < 0 || col1 < 0 || row2 < 0 || col2 < 0) {
                return -1;
            }
            if (grid[row1][col1] == -1 || grid[row2][col2] == -1) {
                return -1;
            }
            if (row1 == 0 && col1 == 0 && row2 == 0 && col2 == 0) {
                over = true;
                return grid[row1][col1];
            }
            int cur = row1 == row2 && col1 == col2 ? grid[row1][col1] : grid[row1][col1] + grid[row2][col2];
            int a = subAns(grid, row1 - 1, col1, row2 - 1, col2);
            int b = subAns(grid, row1, col1 - 1, row2, col2 - 1);
            int c = subAns(grid, row1 - 1, col1, row2, col2 - 1);
            int d = subAns(grid, row1, col1 - 1, row2 - 1, col2);
            int max = Math.max(a, Math.max(b, Math.max(c, d)));
            return cur + max;
        }
    }

    public static void main(String[] args) {
//        int[][] grid = new int[][] {
//                {1,1,1,1,0,0,0},
//                {0,0,0,1,0,0,0},
//                {0,0,0,1,0,0,1},
//                {1,0,0,1,0,0,0},
//                {0,0,0,1,0,0,0},
//                {0,0,0,1,0,0,0},
//                {0,0,0,1,1,1,1},
//        };
        int[][] grid = new int[][]
                {
                        {1,1,1,1,-1,1,-1,-1,-1,0},
                        {1,1,1,0,1,0,0,1,1,-1},
                        {-1,-1,-1,1,1,1,-1,1,1,1},
                        {-1,1,1,1,1,1,1,-1,1,1},
                        {0,0,1,0,1,-1,1,0,1,1},
                        {1,0,1,1,-1,0,0,1,1,0},
                        {1,1,0,1,0,0,1,1,1,-1},
                        {1,1,0,1,1,1,1,1,1,1},
                        {1,0,1,0,-1,1,1,1,1,0},
                        {1,0,-1,0,1,0,1,-1,0,1}
                }
        ;
        int i = new N741_h().new Solution().cherryPickup(grid);
        System.out.println(i);
    }

}
