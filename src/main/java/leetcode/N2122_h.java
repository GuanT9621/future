package leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/recover-the-original-array/
 * 还原原数组
 * Alice 有一个下标从 0 开始的数组 arr ，由 n 个正整数组成。她会选择一个任意的 正整数 k 并按下述方式创建两个下标从 0 开始的新整数数组 lower 和 higher ：
 * 对每个满足 0 <= i < n 的下标 i ，lower[i] = arr[i] - k
 * 对每个满足 0 <= i < n 的下标 i ，higher[i] = arr[i] + k
 * 不幸地是，Alice 丢失了全部三个数组。但是，她记住了在数组 lower 和 higher 中出现的整数，但不知道每个整数属于哪个数组。请你帮助 Alice 还原原数组。
 * 给你一个由 2n 个整数组成的整数数组 nums ，其中 恰好 n 个整数出现在 lower ，剩下的出现在 higher ，还原并返回 原数组 arr 。如果出现答案不唯一的情况，返回 任一 有效数组。
 * 注意：生成的测试用例保证存在 至少一个 有效数组 arr 。
 *
 * 思路 遍历
 * 1 已知存在两两一组的差值相同的数组，那么排序后，一小一大最多范围为 [0 - n / 2]
 * 2 遍历寻找差值相同的数组，k = 差值/2
 */
public class N2122_h {

    public int[] recoverArray(int[] nums) {
        int n = nums.length;
        if (n == 2) {
            return new int[] {(nums[0] + nums[1]) / 2};
        }
        int half = n / 2;
        Arrays.sort(nums);
        int k = getK(nums, n, half);
        int[] ans = new int[half];
        for (int i=0; i < half; i++) {
            ans[i] = nums[i] + k;
        }
        return ans;
    }

    private int getK(int[] nums, int n, int half) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int dk = 0;
        // 一组的跨度
        for (int i=1; i <= half; i++) {
            dk = nums[i] - nums[0];
            for (int j=0; j < n; j++) {
                if (map.getOrDefault(nums[j], 0) == 0) {

                }
            }
        }
        System.out.println(dk);
        return dk / 2;
    }

    public static void main(String[] args) {
        // [2,3,7,8,8,9,9,10]
        int[] nums = {11,6,3,4,8,7,8,7,9,8,9,10,10,2,1,9};
        int[] ints = new N2122_h().recoverArray(nums);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }

}
