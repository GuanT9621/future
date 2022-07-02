package leetcode;

/**
 * https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
 * 找出第 K 小的数对距离
 * 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
 * 给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。返回 所有数对距离中 第 k 小的数对距离。
 * n == nums.length
 * 2 <= n <= 10^4
 * 0 <= nums[i] <= 10^6
 * 1 <= k <= n * (n - 1) / 2
 *
 * 思路 暴力
 * 遍历计算出每一对数的差值，并将其保存到数组，然后取数组第k的作为答案。复杂度为 n!
 * 如果数据量小，暴力即可。看到给出的 2 <= n <= 10^4 条件可知必定超时
 *
 * 思路 二分法
 *
 */
public class N719_h {


    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        // todo
        return 1;
    }

}
