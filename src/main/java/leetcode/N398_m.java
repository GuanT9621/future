package leetcode;

import java.util.Random;

/**
 * https://leetcode-cn.com/problems/random-pick-index/
 * Given an integer array nums with possible duplicates, randomly output the index of a given target number.
 * You can assume that the given target number must exist in the array.
 *
 * Implement the Solution class:
 * Solution(int[] nums) Initializes the object with the array nums.
 * int pick(int target) Picks a random index i from nums where nums[i] == target.
 *      If there are multiple valid i's, then each index should have an equal probability of returning.
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * target is an integer from nums.
 * At most 10^4 calls will be made to pick.
 *
 * Tips 1 Nums is not Orderly
 *
 * 思路 1 HashMap
 * 用 HashMap key 保存数据元素，value 保存该元素索引的数组
 *
 * 思路 2 水塘抽样 Reservoir sampling
 * 所谓`水塘抽样`就是：可否在大小 n 的集合中，随机取出 k 个元素？即每个元素的概率为 k/n
 * k = 1，
 *   选择第一个元素概率1/1，选择第二个元素概率1/2，，，选择第 n 个元素为 1/n；直到遍历整个集合。每个元素 1/n 概率。
 *   对于本题目指定 target 只需要加上该元素是否等于 target 的判断即可。
 * k > 1，
 *   基本原理同上
 *   1 对于前 k 个数，我们保留
 *   2 对于第 k + 1 个数，我们以 k / (k + 1) 的概率保留它，然后替换掉以 1/k 的概率选择的 k 个数中的任意一个
 *   3 对于第 k + 2 个数，我们以 k / (k + 2) 的概率保留它，然后替换掉以 1/k 的概率选择的 k 个数中的任意一个
 *   即对于前k个数，我们全部保留，对于第i（i>k）个数，我们以 k / i 的概率保留第i个数，并以 1 / k 的概率与前面已选择的 k 个数中的任意一个替换。
 */
public class N398_m {

    class Solution {
        int[] nums;
        Random random;

        public Solution(int[] nums) {
            this.nums = nums;
            random = new Random();
        }

        public int pick(int target) {
            int pick = 0;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (target == nums[i]) {
                    count++;
                    if (random.nextInt(count) == 0) {
                        pick = i;
                    }
                }
            }
            return pick;
        }
    }

}
