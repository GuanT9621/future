package leetcode;

import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/minimum-number-of-refueling-stops/
 * 最低加油次数
 * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
 * 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。
 * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
 * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
 * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
 * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
 *
 * 提示：
 * 1 <= target, startFuel, stations[i][1] <= 10^9
 * 0 <= stations.length <= 500
 * 0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target
 *
 * 思路 动态规划
 * 每个加油站都面临着选择 or 不选择。
 * 状态转移方程位 dp[i] = Min(dp[i], dp[i-1]);
 *
 * 思路 优先队列
 * 先拿着当前剩下的油一直跑，跑到没油了，再列出来已经跑过的路程路过的加油站，把油量最多的油“远程”加remain继续跑。
 * 这样的话，每次都是不得不加油时加最多的油，一定是加油次数最少。
 *
 */
public class N871_h {

    static class Solution {

        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            int n = stations.length;
            long[] dp = new long[n + 1];
            dp[0] = startFuel;
            for (int i = 0; i < n; i++) {
                for (int j = i; j >= 0; j--) {
                    if (dp[j] >= stations[i][0]) {
                        dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i][1]);
                    }
                }
            }
            for (int i = 0; i <= n; i++) {
                if (dp[i] >= target) {
                    return i;
                }
            }
            return -1;
        }

    }

    class Solution2 {
        public int minRefuelStops(int t, int start, int[][] ss) {
            PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
            int n = ss.length, idx = 0;
            int remain = start, loc = 0, ans = 0;
            while (loc < t) {
                if (remain == 0) {
                    if (!q.isEmpty() && ++ans >= 0)
                        remain += q.poll();
                    else
                        return -1;
                }
                loc += remain;
                remain = 0;
                while (idx < n && ss[idx][0] <= loc)
                    q.add(ss[idx++][1]);
            }
            return ans;
        }
    }

}
