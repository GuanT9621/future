package leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element/
 * 元素的 频数 是该元素在一个数组中出现的次数。
 * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 *
 * 思路：先排序，然后滑动窗口
 * 滑动窗口的逻辑是：
 * 先从第一个开始，判断第一个是否能补全到第二个，如果可以，那么看看第一二是否可以补全到第三个，以此类推。
 * 然后循环到第二个元素，最后得到最大频次
 *
 * 优化方案 数组里有 n 个数，那么最大可能的频次为 n，该数为 nums[n-1]，所以可从 n 元素开始，倒叙推导。
 *
 */
public class N1838_m {

    public static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long total = 0;
        int left = 0; // 左窗口
        int result = 1;
        for (int right = 1; right < n; right++) { // 右窗口
            total = total + (long) (nums[right] - nums[right - 1]) * (right - left);
            while (total > k) { // 当前窗口不符合要求，收缩左窗口，每次收缩重新计算total，直到满足要求
                total = total - (nums[right] - nums[left]);
                left++;
            }
            result = Math.max(result, right - left + 1); // 更新本次结果
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,9,6};
        int k = 2;
        int x = maxFrequency(nums, k);
        System.out.print(x);
    }

}
