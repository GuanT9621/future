package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/JEj789/
 * https://leetcode-cn.com/problems/paint-house/
 * 粉刷房子
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的正整数矩阵 costs 来表示的。
 * 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。
 * 请计算出粉刷完所有房子最少的花费成本。
 * costs.length == n    costs[i].length == 3    1 <= n <= 100   1 <= costs[i][j] <= 20
 *
 * 思路 暴力搜索
 * 本质上是一个多叉树，每个叶子节点代表着一种方案。
 *
 * 思路 动态规划
 * 经典的动态规划问题，状态转移为每次选择颜色就向下一个层进行询问，然后在本层处理本层的最小选择。
 * 三种颜色的情况可以合并为一个状态转移方程，对于 1 ≤ i < n 和 0 ≤ j < 3，状态转移方程如下：
 * dp[i][j] = min(dp[i−1][(j+1)mod3], dp[i−1][(j+2)mod3]) + costs[i][j]
 *
 */
public class N256_m {

    public int minCost(int[][] costs) {
        int ans0 = costs[0][0] + minCost(costs, 0, 0);
        int ans1 = costs[0][1] + minCost(costs, 0, 1);
        int ans2 = costs[0][2] + minCost(costs, 0, 2);
        return Math.min(Math.min(ans0, ans1), ans2);
    }
    private int minCost(int[][] costs, int index, int exclude) {
        index++;
        if (costs.length <= index) {
            return 0;
        }
        if (exclude == 0) {
            return Math.min(
                    costs[index][1] + minCost(costs, index, 1),
                    costs[index][2] + minCost(costs, index, 2));
        }
        if (exclude == 1) {
            return Math.min(
                    costs[index][0] + minCost(costs, index, 0),
                    costs[index][2] + minCost(costs, index, 2));
        }
        if (exclude == 2) {
            return Math.min(
                    costs[index][0] + minCost(costs, index, 0),
                    costs[index][1] + minCost(costs, index, 1));
        }
        return 0;
    }

    public int minCost2(int[][] costs) {
        // 前缀和，利用原有数组，原地计算。
        int n = costs.length;
        for(int i = 1; i < n; i++){
            costs[i][0] += Math.min(costs[i - 1][2], costs[i - 1][1]);
            costs[i][1] += Math.min(costs[i - 1][2], costs[i - 1][0]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        return Math.min(costs[n - 1][0], Math.min(costs[n - 1][1], costs[n - 1][2]));
    }

    public int minCost3(int[][] costs) {
        // dp
        int n = costs.length;
        int[] dp = new int[3];
        for (int j = 0; j < 3; j++) {
            dp[j] = costs[0][j];
        }
        for (int i = 1; i < n; i++) {
            int[] dpNew = new int[3];
            for (int j = 0; j < 3; j++) {
                dpNew[j] = Math.min(dp[(j + 1) % 3], dp[(j + 2) % 3]) + costs[i][j];
            }
            dp = dpNew;
        }
        return Arrays.stream(dp).min().getAsInt();
    }

}
