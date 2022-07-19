package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/k-diff-pairs-in-an-array/
 * 数组中的 k-diff 数对
 * 给定一个整数数组和一个整数 k，你需要在数组里找到 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
 * 这里将 k-diff 数对定义为一个整数对 (nums[i], nums[j])，并满足下述全部条件：
 *     0 <= i < j < nums.length
 *     |nums[i] - nums[j]| == k
 *
 * 1 <= nums.length <= 10^4
 * -10^7 <= nums[i] <= 10^7
 * 0 <= k <= 10^7
 *
 * 思路 暴力
 * 遍历计算出每一对数的差值，并将其保存到数组，然后取 k 作为答案。复杂度为 n!
 * 如果数据量小，暴力即可。看到给出的 1 <= n <= 10^4 条件可知必定超时
 *
 * 思路 排序 + 二分法
 * 排序后的数之间的diff肯定是越来越来大的，
 * 依靠这个原理加上二分法就可以快速找到满足的
 *
 */
public class N532_m {

    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        // todo
        return 0;
    }

}
