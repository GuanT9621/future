package leetcode;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
 * 删除有序数组中的重复项 II
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 这种类似的题目，第一反应是双指针法
 * 从2位置开始比较 slow-2 和 fast，一样时，指针一起前移
 * 不一样时，用快指针覆盖慢指针数据，慢指针不动，快指针前移
 */
public class N80_m {

    public static int removeDuplicates(int[] nums) {
        if (nums.length < 3) return nums.length;
        int slow = 2, fast = 2;
        while (fast < nums.length) {
            if (nums[slow-2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] ints = {1, 1, 2, 2, 2, 3, 3};
        int len = removeDuplicates(ints);
        System.out.println(len);
        for (int i = 0; i < len; i++)
            System.out.print(ints[i]);
    }

}
