package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/find-good-days-to-rob-the-bank/
 * 适合打劫银行的日子
 * 你和一群强盗准备打劫银行。
 * 给你一个下标从 0 开始的整数数组 security ，其中 security[i] 是第 i 天执勤警卫的数量。日子从 0 开始编号。同时给你一个整数 time 。
 * 如果第 i 天满足以下所有条件，我们称它为一个适合打劫银行的日子：
 * 第 i 天前和后都分别至少有 time 天。
 * 第 i 天前连续 time 天警卫数目都是非递增的。
 * 第 i 天后连续 time 天警卫数目都是非递减的。
 * 更正式的，第 i 天是一个合适打劫银行的日子当且仅当：
 * security[i - time] >= security[i - time + 1] >= ... >= security[i] <= ... <= security[i + time - 1] <= security[i + time].
 * 请你返回一个数组，包含 所有 适合打劫银行的日子（下标从 0 开始）。返回的日子可以 任意 顺序排列。
 * 条件 ： 1 <= security.length <= 105     0 <= security[i], time <= 105
 *
 * 思路 预处理-前缀和思想
 * 答案 i 范围在 [time, n-time]， 且 (i - time , i] 内非递增，[i, i + time) 内非递减。
 * 用数组 g 记录每个元素的的增减信息，1 增 -1 减 0 不增不减
 * 用数组 a 记录递增的，即 1 的数量
 * 用数组 b 记录递减的，即 -1 的数量
 * 判断 i 在 a 里是非递增的，即 a[i] - a[i - time] = 0
 * 判断 i 在 b 里是非递减的，即 b[i + time] - b[i] = 0
 *
 * 思路 预处理-动态规划 dp
 * 设第 i 天前警卫数目连续非递增的天数为 left(i)，第 i 天后警卫数目连续非递减的天数为 right(i)，
 * 当第 i 天同时满足 left(i) ≥ time, right(i) ≥ time 时，即可认定第 i 天适合打劫。
 */
public class N2100_m {

    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int length = security.length;
        List<Integer> ans = new ArrayList<>();
        if (time == 0) {
            for (int i=0; i < length; i++) {
                ans.add(i);
            }
            return ans;
        }
        if (length < time * 2 + 1) {
            return ans;
        }
        // 处理完特殊情况
        int[] g = new int[length];
        for (int i=1; i < length; i++) {
            if (security[i - 1] == security[i]) {
                continue;
            }
            g[i] = security[i - 1] < security[i] ? 1 : -1;
        }
        int[] a = new int[length + 1];
        int[] b = new int[length + 1];
        for (int i=1; i <= length; i++) {
            a[i] = a[i - 1] + (g[i - 1] == 1 ? 1 : 0);
            b[i] = b[i - 1] + (g[i - 1] == -1 ? 1 : 0);
        }
        for (int i=time; i < length - time; i++) {
            if (a[i + 1] - a[i + 1 - time] == 0 && b[i + 1 + time] - b[i + 1] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }

    public List<Integer> goodDaysToRobBankDP(int[] security, int time) {
        int n = security.length;
        int[] left = new int[n]; // 非递增 >
        int[] right = new int[n]; // 非递减 <
        for (int i = 1; i < n; i++) {
            if (security[i] <= security[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
            if (security[n - i - 1] <= security[n - i]) {
                right[n - i - 1] = right[n - i] + 1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = time; i < n - time; i++) {
            if (left[i] >= time && right[i] >= time) {
                ans.add(i);
            }
        }
        return ans;
    }
}
