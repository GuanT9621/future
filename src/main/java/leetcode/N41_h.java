package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/first-missing-positive/
 * 缺失的第一个正数
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *
 * 思路一 从 1 开始暴力判断，将 nums 存入哈希表，时间复杂度和空间负责度都不满足。
 *
 * 思路二 排序，判断
 * 预设 ans = 1，如果存在等于 ans 的，将 ans++
 *
 */
public class N41_h {

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        if (nums[0] > 1 || nums[n - 1] < 1) {
            return 1;
        }
        int ans = 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == ans) {
                ans++;
            }
        }
        return ans;
    }

}
