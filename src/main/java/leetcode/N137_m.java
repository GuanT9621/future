package leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/single-number-ii/
 * 只出现一次的数字 II
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 思路一 哈希表 O(n) O(n)
 * 思路二 先排序，后遍历
 * 思路三 依次确定每一个二进制位，将nums的全部元素每一位相加，然后除以3，得到的余数为答案的结果。 O(nlogC)O(1)
 *          难点：根据真值表可以设计出电路：
 *          解决：使用卡诺图来设计电路
 *          a = (~a & b & x) | (a & ~b & ~x)
 *          b = ~a & (b ˆ x)
 *
 * 思路四 数字电路设计，同思路三 ：以普通的二元运算为基础，同时处理所有的二进制位
 *       优化：方法三中计算 b 的规则较为简单，而 a 的规则较为麻烦，因此可以将「同时计算」改为「分别计算」，即先计算出 b，再拿新的 b 值计算 a。
 */
public class N137_m {

    public static int singleNumber(int[] nums) {
        if (1 == nums.length)
            return nums[0];
        Arrays.sort(nums);
        for (int i=0; i<nums.length; ) {
            if (i+3 < nums.length && nums[i] == nums[i+2]) {
                i+=3;
            } else {
                return nums[i];
            }
        }
        throw new RuntimeException("Not found");
    }

    public static int singleNumber1(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            int aNext = (~a & b & num) | (a & ~b & ~num);
            int bNext = ~a & (b ^ num);
            a = aNext;
            b = bNext;
        }
        return b;
    }
    public static int singleNumber2(int[] nums) {// 方法五
        int a = 0, b = 0;
        for (int num : nums) {
            b = ~a & (b ^ num);
            a = ~b & (a ^ num);
        }
        return b;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,1,0,1,99};
        int i = singleNumber2(nums);
        System.out.println(i);
    }

}
