package leetcode;


import java.util.Arrays;

/**
 * https://leetcode.cn/problems/wiggle-sort-ii/
 * 摆动排序 II
 * 给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 * 1 <= nums.length <= 5 * 10^4  0 <= nums[i] <= 5000
 * 进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 *
 * 思路 不限制
 * 将数组排序后，从分别从位置0，位置n/2（向上取整），取值依次填充。
 * 这样无法处理 4 5 5 6，所以倒序取，从n，n/2 取。
 *
 * 思路 O(n) 时间复杂度
 * 使用新数组，从原数组遍历取放入新数组
 *
 * 思路 原地 O(1) 额外空间
 * 取最小值交换放到位置0，取最大值交换放到位置1，然后对剩下的数组[2, n] 以此类推即可。
 *
 */
public class N324_m {

    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int end = nums.length - 1;
        int half = (nums.length - 1) / 2;
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = i % 2 == 0 ? nums[half--] : nums[end--];
        }
        System.arraycopy(ans, 0, nums, 0, nums.length);
    }

    public void wiggleSortOn(int[] nums) {

    }

    // nums = [1,3,2,2,3,1]
    // 输出：[2,3,1,3,1,2]
    //      [1,3,1,3,2,2]
    public void wiggleSortO1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                int min = i;
                for (int j = i; j < nums.length; j++) {
                    min = nums[j] < nums[min] ? j : min;
                }
                swap(nums, i, min);
            } else {
                int max = i;
                for (int j = i; j < nums.length; j++) {
                    max = nums[j] > nums[max] ? j : max;
                }
                swap(nums, i, max);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

}
