package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/ur2n8P/
 *
 * 思路
 * 唯一的最短 超序列：
 * 唯一意味着 每个数字的对应的位置都是确定的
 * 最短意味着 nums里没有多余的数字
 * 为了判断 nums 是不是序列的唯一最短超序列，只需要判断根据 sequences 中的每个序列构造超序列的结果是否唯一
 *
 */
public class N444_m {

    class Solution {
        public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
            int m = nums.length;
            int[] counts = new int[m + 1];
            Arrays.fill(counts, m - 1);
            counts[0] = 0;
            for (int[] sequence : sequences) {
                for (int i : sequence) {
                    counts[i] -= 1;
                }
            }
            int still = 0;
            for (int count : counts) {
                still += count;
            }
            return still == 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,1,5,2,6,3};
        int[][] sequences = new int[][] {{5,2,6,3},{4,1,5,2}};
        boolean b = new N444_m().new Solution().sequenceReconstruction(nums, sequences);
        System.out.println(b);

    }

}
