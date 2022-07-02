package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/
 * 最少移动次数使数组元素相等 II
 * 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最少移动数。
 * 在一步操作中，你可以使数组中的一个元素加 1 或者减 1 。
 * tips  1 <= nums.length <= 10^5    -10^9 <= nums[i] <= 10^9
 *
 * 思路 排序 + 双指针 + 中位数
 *
 * 首先排除暴力求平均值
 * 先排序，然后假设 1，7，8，9，10 五个数，
 * 你会发现，当你考虑 1，10 时，靠拢的数 flag 肯定在 1-10之间，因为在这个范围之内时移动数最少的，
 * 而这时无论将 flag 在1-10中怎么移动，移动数都是相同的 (flag - 1) + (10 - flag) = 10 - 1 = 9
 * 同理，下一轮，仅考虑 7，9 移动数为 9 - 7 = 2
 * 在下一轮，移动数为 8 - 8 = 0
 * 总计 9 + 2 + 0 = 11
 */
public class N462_m {

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int ans = 0;
        while (left <= right) {
            ans += (nums[right] - nums[left]);
            left++;
            right--;
        }
        return ans;
    }

}
