package leetcode;

/**
 * https://leetcode-cn.com/problems/coin-change-2/
 * 零钱兑换 II
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个
 * 题目数据保证结果符合 32 位带符号整数。
 *
 * 思路一  动态规划  dp[x] 表示金额之和等于 x 的硬币组合数
 *        1 状态转移方程 dp[i] += dp[i-coin];
 *        2 边界条件 dp[0] = 1
 *
 * 思路，从0个元素开始思考，0个元素只有一种方案
 */
public class N518_M {

    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i=coin; i<=amount; i++) {
                dp[i] += dp[i-coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = new int[] {1};
        int change = change(amount, coins);
        System.out.println(change);
    }
}
