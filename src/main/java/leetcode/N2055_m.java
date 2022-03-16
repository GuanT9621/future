package leetcode;

/**
 * https://leetcode-cn.com/problems/plates-between-candles/
 * 蜡烛之间的盘子
 * 给你一个长桌子，桌子上盘子和蜡烛排成一列。
 * 给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|' 表示一支 蜡烛 。
 * 同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[lefti...righti] （包含左右端点的字符）。
 * 对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。如果一个盘子在 子字符串中 左边和右边 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。
 *
 * 比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。
 * 子字符串中在两支蜡烛之间的盘子数目为 2 ，子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
 * 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
 * 条件 3 <= s.length <= 10^5  1 <= queries.length <= 10^5  queries[i].length == 2  0 <= lefti <= righti < s.length
 *
 * 思路 模拟
 * 按照题目要求模拟寻找的答案即可
 *
 * 思路 前缀和
 * 典型的前缀和的应用场景
 * 超时优化，right需要找它左侧的｜，left需要找它右侧的｜，那么我们用两个数组分别记录当前位置的下一个｜在哪儿。
 */
public class N2055_m {

    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] preSum = new int[n];
        preSum[0] = 0;
        int preI = 0;
        for (int i = 1; i < n; i++) {
            if ('|' == chars[i]) {
                preSum[i] = preSum[i - 1] + (i - preI - 1);
                preI = i;
            } else {
                preSum[i] = preSum[i - 1];
            }
        }
        int[] lefts = new int[n];
        for (int i=n - 1, l=-1; i >= 0; i--) {
            if (chars[i] == '|') {
                l = i;
            }
            lefts[i] = l;
        }
        int[] rights = new int[n];
        for (int i=0, r=-1; i < n; i++) {
            if (chars[i] == '|') {
                r = i;
            }
            rights[i] = r;
        }
        int[] ans = new int[queries.length];
        for (int i=0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            if (preSum[right] == preSum[left]) {
                ans[i] = 0;
            } else {
                right = rights[right];
                left = lefts[left];
                ans[i] = left < right ? preSum[right] - preSum[left] : 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "||**||**|*";
        int[][] queries = new int[][] {{3, 8}};
        int[] ints = new N2055_m().platesBetweenCandles(s, queries);
        for (int a : ints) {
            System.out.println(a);
        }
    }

}
