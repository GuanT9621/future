package leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/
 * 学生分数的最小差值
 * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
 * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。返回可能的 最小差值 。
 * 1 <= k <= nums.length <= 1000
 * 0 <= nums[i] <= 10^5
 *
 * 思路一 排序 + 滑动窗口k
 * O n logn
 * O logn
 */
public class N1984_e {

    public static int minimumDifference(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for (int i=0; i+k-1 < nums.length; i++) {
            ans = Math.min(ans, nums[i+k-1]-nums[i]);
        }
        return ans;
    }

}
