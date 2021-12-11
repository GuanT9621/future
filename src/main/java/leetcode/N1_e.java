package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum/
 * 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * 思路 关键点 两数
 * 思路一 双For循环，O(n方)，外循环设置一个X，在内循环中找 target - X
 * 思路二 hashMap，减少内循环寻找 target - X 的复杂度
 */
public class N1_e {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(target-num)) {
                return new int[]{i, map.get(target-num)};
            }
            map.put(num, i);
        }
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        int[] ints = {1, 1, 2};
        int[] ints1 = twoSum(ints, 2);
        for (int i : ints1) System.out.println(i);
    }
}
