package leetcode;

/**
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 *      时间复杂度为 O(log n) 的算法解决此问题吗？
 *
 * 题目解析，其实就是让你寻找第一个大于等于target的位置，第一个大于target的位置-1；
 * 思路一：遍历   O(n)
 * 思路二：二分法 O(log n)
 */
public class N34_m {

    /** 遍历 ： 小心处理 [] [2] [2,2] 边界 */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums.length == 0)
            return result;
        boolean first = true;
        for (int i=0; i<nums.length; i++) {
            if (first) {
                if (nums[i] == target) {
                    result[0] = i;
                    first = false;
                }
            } else {
                if (nums[i] != target) {
                    result[1] = i-1;
                    break;
                }
            }
        }
        if (nums[nums.length-1] == target)
            result[1] = nums.length-1;
        return result;
    }

    /**二分法 O logN */
    public static int[] searchRange2(int[] nums, int target) {
        int leftP = binarySearch(nums, target, true);
        int rightP = binarySearch(nums, target, false) - 1;
        if (leftP <= rightP && rightP < nums.length
                && nums[leftP] == target && nums[rightP] == target) {
            return new int[]{leftP, rightP};
        }
        return new int[]{-1, -1};
    }

    public static int binarySearch(int[] nums, int target, boolean lower) {
        int l = 0, r = nums.length - 1, ans = nums.length;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                r = mid - 1;
                ans = mid;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[]nums = new int[]{5,7,7,8,8,10};
        int target = 8;
        int[] ints = searchRange2(nums, target);
        for (int i : ints)
            System.out.print(i + " ");
    }
}
