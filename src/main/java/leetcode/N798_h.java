package leetcode;

/**
 * https://leetcode-cn.com/problems/smallest-rotation-with-highest-score/
 * 得分最高的最小轮调
 * 给你一个数组 nums，我们可以将它按一个非负整数 k 进行轮调，这样可以使数组变为 [nums[k], nums[k + 1],
 * ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]] 的形式。此后，任何值小于或等于其索引的项都可以记作一分。
 *
 * 例如，数组为 nums = [2,4,1,3,0]，我们按 k = 2 进行轮调后，它将变成 [1,3,0,2,4]。
 * 这将记为 3 分，因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
 * 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。
 *
 * 条件 1 <= nums.length <= 10^5   0 <= nums[i] < nums.length
 *
 * 推理 依题可知，
 * 最低分为 1，如 [1, 2, 3, 0]；   最高分为 nums.length，如 [0, 1, 2, 3]；   轮调次数 k 的范围为[0, nums.length]
 *
 * 思路 暴力-循环
 * 基本可以肯定运算会超时
 *
 * 思路 差分数组
 * 1 对于数组 nums 中的元素 x，当 x 所在下标大于或等于 x 时，元素 x 会记 1 分。
 *   因此元素 x 记 1 分的下标范围是 [x, n - 1]，有 n - x 个下标，元素 x 不计分的下标范围是 [0, x - 1]，有 x 个下标。
 * 2 我们可以计算出元素 x 下标为 i 在 k 轮调下，得 1 分的 k 的范围
 *   当 i < x 时，i + 1 ≤ k ≤ i − x + n；
 *   当 i ≥ x 时，k ≥ i+1 或 k ≤ i−x。
 * 3 遍历每个元素的 k 范围，得到数组的下标的得分，计 1 分的元素个数最多的轮调下标即为得分最高的轮调下标
 *
 */
public class N798_h {

    public int bestRotation(int[] nums) {
        int n = nums.length;
        // 计算差分数组
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            int low = (i + 1) % n;
            int high = (i - nums[i] + n + 1) % n;
            diffs[low]++;
            diffs[high]--;
            if (low >= high) {
                diffs[0]++;
            }
        }
        int bestIndex = 0;
        int maxScore = 0;
        int score = 0;
        for (int i = 0; i < n; i++) {
            score += diffs[i];
            if (score > maxScore) {
                bestIndex = i;
                maxScore = score;
            }
        }
        return bestIndex;
    }

}
