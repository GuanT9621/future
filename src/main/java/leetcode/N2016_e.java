package leetcode;

/**
 * https://leetcode-cn.com/problems/maximum-difference-between-increasing-elements/
 * 增量元素之间的最大差值
 * 给你一个下标从 0 开始的整数数组 nums ，该数组的大小为 n ，请你计算 nums[j] - nums[i] 能求得的 最大差值 ，
 * 其中 0 <= i < j < n 且 nums[i] < nums[j] 。返回 最大差值 。如果不存在满足要求的 i 和 j ，返回 -1 。
 *
 * 思路一 暴力枚举
 * 双重循环 + 对比
 *
 * 思路二 前缀最小值
 * 当我们固定 j 时，选择的下标 i 一定是满足 0≤i<j 并且 nums[i] 最小的那个 i。
 * 因此我们可以使用循环对 j 进行遍历，同时维护 nums[0..j−1] 的前缀最小值，记为 premin。
 * 这样一来：如果 nums[i]>premin，那么就用 nums[i]−premin 对答案进行更新；
 *          否则，用 nums[i] 来更新前缀最小值 premin。
 */
public class N2016_e {

    public int maximumDifference(int[] nums) {
        int ans = -1;
        int premin = nums[0];
        for (int i=1; i<nums.length; i++) {
            if (nums[i] <= premin) {
                premin = nums[i];
            } else {
                ans = Math.max(ans, nums[i] - premin);
            }
        }
        return ans;
    }

    public int maximumDifference2(int[] nums) {
        int ans = -1;
        for (int i=0; i<nums.length; i++) {
            for (int j=i; j<nums.length; j++) {
                if (nums[j] > nums[i]) {
                    ans = Math.max(ans, nums[j] - nums[i]);
                }
            }
        }
        return ans;
    }

}
