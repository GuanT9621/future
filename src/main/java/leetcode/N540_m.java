package leetcode;

/**
 * https://leetcode-cn.com/problems/single-element-in-a-sorted-array/
 * 有序数组中的单一元素
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。请你找出并返回只出现一次的那个数。
 *
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 *
 * 思路一 异或
 * 思路二 双指针求和值
 * 思路三 二分查找
 * 思路四 偶数下标的二分查找
 *          只出现一次的元素所在下标 x 的左边有偶数个元素，因此下标 x 一定是偶数，可以在偶数下标范围内二分查找。
 *          二分查找的目标是找到满足 nums[x] != nums[x+1] 的最小的偶数下标 x，则下标 x 处的元素是只出现一次的元素。
 */
public class N540_m {

    public static int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int i = 0;
        int j = nums.length-1;
        while (i < j) {
            if (nums[i]+nums[j] != nums[i+1]+nums[j-1]) {
                if (nums[i] == nums[i+1]) {
                    return nums[j];
                } else {
                    return nums[i];
                }
            }
            i += 2;
            j -= 2;
        }
        return nums[i];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2,3,3};
        int i = singleNonDuplicate(nums);
        System.out.println(i);
    }

}
