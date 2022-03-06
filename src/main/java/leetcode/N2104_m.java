package leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/sum-of-subarray-ranges/
 * 子数组范围和
 * 给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。返回 nums 中 所有 子数组范围的 和 。
 * 子数组是数组中一个连续非空的元素序列。
 * 提示：1 <= nums.length <= 1000      -109 <= nums[i] <= 109
 *
 * 思路一 遍历子数组 O(n^2) O(1)
 * 1 遍历获得全部的子数组
 * 2 计算所有子数组的差值，然后累加
 *
 * 思路二 单调栈 O(n) O(n)
 * 1 子数组范围的和也等于全部最大值的和减去全部最小值的和
 * 2 遍历每一个元素，每次更新向最大值和、最小值和（用单调增大栈、单调减小栈做辅助）；最后做相减
 */
public class N2104_m {

    // 暴力
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        long ans = 0L;
        for (int i=0; i < n; i++) {
            int max = nums[i];
            int min = nums[i];
            for (int j=i; j < n; j++) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                ans += max - min;
            }
        }
        return ans;
    }

    // 单调栈
    public long subArrayRangesStack(int[] nums) {
        Stack<Integer> maxStack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();
        maxStack.push(nums[0]);
        minStack.push(nums[0]);
        long maxSum = 0L;
        long minSum = 0L;
        for (int i=1; i < nums.length; i++) {
            if (maxStack.peek() < nums[i]) {
                maxStack.push(nums[i]);
            }
            if (minStack.peek() > nums[i]) {
                minStack.push(nums[i]);
            }
            maxSum += maxStack.peek();
            minSum += minStack.peek();
        }
        return maxSum - minSum;
    }

    // nums = [4,-2,-3,4,1] 输出：59
    public static void main(String[] args) {
        int[] nums = new int[]{4,-2,-3,4,1};
        long l = new N2104_m().subArrayRanges(nums);
        long l2 = new N2104_m().subArrayRangesStack(nums);
        System.out.println(l);
        System.out.println(l2);
    }

}
