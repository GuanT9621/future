package leetcode;

/**
 * https://leetcode-cn.com/problems/maximum-product-of-three-numbers/
 * 三个数的最大乘积
 * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * 思路：最大乘积可能为正数，负数(全都是负数)；
 * 如果数组中全是非负数，则排序后最大的三个数相乘即为最大乘积；如果全是非正数，则最大的三个数相乘同样也为最大乘积。
 * 如果数组中有正数有负数，则最大乘积既可能是三个最大正数的乘积，也可能是两个最小负数（即绝对值最大）与最大正数的乘积。
 * max1 max2 max3 min1 min2
 */
public class N628_e {

    public static int maximumProduct(int[] nums) {
        if (0 == nums.length) return 0;
        if (1 == nums.length) return nums[0];
        if (2 == nums.length) return nums[0] * nums[1];
        if (3 == nums.length) return nums[0] * nums[1] * nums[2];
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int i : nums) {
            if (i < min1) {
                min2 = min1;
                min1 = i;
            } else if (i < min2) {
                min2 = i;
            }
            if (i > max1) {
                max3 = max2;
                max2 = max1;
                max1 = i;
            } else if (i > max2) {
                max3 = max2;
                max2 = i;
            } else if (i > max3) {
                max3 = i;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

    public static void main(String[] args) {
        int i = maximumProduct(new int[]{1, 2, 3});
        System.out.println(i);
    }

}
