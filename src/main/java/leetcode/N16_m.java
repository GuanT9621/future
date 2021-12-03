package leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/3sum-closest/
 * 最接近的三数之和
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在恰好一个解。
 *
 * 思路
 * 排序 & 双指针
 * 排序后，从固定第一个元素开始，然后从两头指针开始，每次计算下当前的三个点之和sum，并和result比较，取接近target的
 * 然后，如果当前之和sum 大于 target，右指针左移（sum变小），否则坐指针右移（sum变大）
 * 最终，循环完每一个元素作为固定元素，则result是最接近的三点和。
 */
public class N16_m {

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];

        for (int i=0; i<nums.length-2; i++) {
            int left = i+1;
            int right = nums.length - 1;
            while (left != right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
                if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        int i = threeSumClosest(nums, target);
        System.out.println(i);
    }

}
