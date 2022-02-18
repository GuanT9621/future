package leetcode;

/**
 * https://leetcode-cn.com/problems/knight-probability-in-chessboard/
 * 骑士在棋盘上的概率
 * 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 * 骑士继续移动，直到它走了 k 步或离开了棋盘。返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
 * 1 <= n <= 25     0 <= k <= 100       0 <= row, column <= n
 *
 * 思路1 暴力递归
 * 每次算出当前的停留的点，递归这些点，计算当前的概率
 *
 * 思路2 记忆化搜索
 *
 * 思路3 动态规划
 * dp[step][i][j] 表示骑士从棋盘上的点(i,j)出发，走了step步时仍然留在棋盘上的概率
 * dp[0][i][j]=1
 * dp[step][i][j] = 1/8 x ∑di,dj dp[step−1][i+di][j+dj]，其中(di,dj)表示走法对坐标的偏移量，具体为(−2,−1),(−2,1),(2,−1),(2,1),(−1,−2),(−1,2),(1,−2),(1,2) 共 88 种。
 */
public class N688_m {
    // 暴力
    private static final int[][] DIRS = {{1, 2}, {2, 1}, {-1, 2}, {2, -1}, {1, -2}, {-2, 1}, {-1, -2}, {-2, -1}};
    public double knightProbability1(int n, int k, int row, int column) {
        return dfs1(n, k, row, column);
    }
    public double dfs1(int n, int k, int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n)
            return 0;
        if (k == 0)
            return 1;
        double ans = 0;
        for (int[] dir : DIRS)
            ans += dfs1(n, k - 1, i + dir[0], j + dir[1]) / 8;
        return ans;
    }

    // 记忆化搜索
    static int[][] dirs = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};
    static double[][][] dp = new double[26][26][101];
    public double knightProbability2(int n, int k, int row, int column) {
        return dfs2(n, k, row, column);
    }
    private double dfs2(int n, int k, int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n)
            return 0;
        if (k == 0)
            return 1;
        if (dp[i][j][k] != 0)
            return dp[i][j][k];
        for (int[] dir : dirs)
            dp[i][j][k] += dfs2(n , k-1, i + dir[0], j + dir[1]) / 8;
        return dp[i][j][k];
    }

    // dp
    static int[][] dirs3 = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};
    public double knightProbability3(int n, int k, int row, int column) {
        double[][][] dp = new double[k + 1][n][n];
        for (int step=0; step<=k; step++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (step == 0) {
                        dp[step][i][j] = 1;
                    } else {
                        for (int[] dir : dirs3) {
                            int ni = i + dir[0];
                            int nj = j + dir[1];
                            if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                                dp[step][i][j] += dp[step - 1][ni][nj] / 8;
                            }
                        }
                    }
                }
            }
        }
        return dp[k][row][column];
    }

    public static void main(String[] args) {
        int n = 10;
        int k = 13;
        int r = 5;
        int c = 3;
        N688_m n688_m = new N688_m();
//        System.out.println(n688_m.knightProbability1(n, k ,r, c));
        System.out.println(n688_m.knightProbability2(n, k ,r, c));
        System.out.println(n688_m.knightProbability3(n, k ,r, c));
    }
}
