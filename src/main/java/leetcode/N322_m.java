package leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/coin-change/
 * 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 *
 * 这道题目子问题是，problem(i) = Min(sub(i-coin1), sub(i-coin2), sub(i-coinX))
 * 转换下 DP[i] = Min(DP[i-j]) + 1, 其中j = coin1， coin2、、、coinX
 *
 * 例如 coins = [1, 2, 5], amount = 11
 * F(i)	 最小硬币数量
 * F(0)	 0 // 金额为0不能由硬币组成
 * F(1)	 1 // F(1)=min(F(1−1),F(1−2),F(1−5))+1=1
 * F(2)	 1 // F(2)=min(F(2−1),F(2−2),F(2−5))+1=1
 * F(3)	 2 // F(3)=min(F(3−1),F(3−2),F(3−5))+1=2
 * F(4)	 2 // F(4)=min(F(4−1),F(4−2),F(4−5))+1=2
 * ...	 ...
 * F(11) 3 // F(11)=min(F(11−1),F(11−2),F(11−5))+1=3
 *
 */
public class N322_m {

    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i=1; i<=amount; i++) {
            for (int coin : coins) {
                if (i >= coin)
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int amount = 27;
        int[] coins = new int[] {2, 5, 10, 1};
        int change = coinChange(coins, amount);
        System.out.println(change);
    }

}
