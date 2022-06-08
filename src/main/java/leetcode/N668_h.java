package leetcode;

/**
 * https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table/
 * 乘法表中第k小的数
 * 几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？
 * 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。
 * m 和 n 的范围在 [1, 30000] 之间。k 的范围在 [1, m * n] 之间。所以不要试图暴力解决了。
 *
 * 总结：第k小、第k大的数 解法基本是：优先队列、最大/小顶堆、二分查找法
 *
 * 思路 二分查找法
 * x1  1 2 3 4
 * x2    2   4   6   8
 * x3      3     6     9       12
 * x4        4       8         12          16
 *
 * 求第几小等价于求有多少数字不超过 x。我们可以遍历乘法表的每一行，对于乘法表的第 i 行，其数字均为 i 的倍数，
 * 因此不超过 x 的数字有 min(⌊x/i⌋, n) 个，所以整个乘法表不超过 x 的数字个数为
 * m
 * ∑ min(⌊x/i⌋, n)
 * i=1
 *
 * 由于 i ≤ ⌊x/n⌋ 时 ⌊x/i⌋ ≥ n，上式可化简为
 *             m
 * ⌊x/n⌋ * n + ∑ ⌊x/i⌋
 *            i=⌊x/n⌋+1
 *
 * 由于 x 越大上式越大，x 越小上式越小，因此我们可以二分 x 找到答案，二分的初始边界为乘法表的元素范围，即 [1,mn]
 */
public class N668_h {

    public int findKthNumber(int m, int n, int k) {
        int left = 1;
        int right = m * n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = mid / n * n;
            for (int i = mid / n + 1; i <= m; i++) {
                count += mid / i;
            }
            if (count >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

}
