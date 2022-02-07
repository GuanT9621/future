package leetcode;

/**
 * https://leetcode-cn.com/problems/climbing-stairs/
 * 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 思路一 动态规划
 *
 * 这道题目子问题是，problem(i) = sub(i-1) + sub(i-2), 即求解第i阶楼梯等于求解第 i-1 阶楼梯和第 i-2 阶楼梯之和
 * 状态数组是 DP[i], 状态转移方程是 DP[i] = DP[i-1] + DP[i-2]
 * 边界条件是 DP[0] = 1, DP[1] = 1;
 *
 * 拓展：如果我们把问题泛化，不再是固定的 1，2，而是任意给定台阶数，例如 1,2,5 呢？
 * 那么 DP[i] = DP[i-1] + DP[i-2] + DP[i-5], 也就是 DP[i] = DP[i] + DP[i-j] ,j =1,2,5
 *
 */
public class N70_m {

    // 1 <= n <= 45
    public static int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 拓展，每次你可以爬 steps 个台阶，steps 为数组
    // 计算结果为全排列数
    public static int climbStairs(int[] steps, int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            // 计算 DP[i] = DP[i] + DP[i-j] ,j =1,2,5
            for (int step : steps) {
                if (i >= step)
                    dp[i] = dp[i] + dp[i - step];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 22;
        int[] steps = {1, 2};
        int i = climbStairs(steps, n);
        System.out.println(i);
    }

}
