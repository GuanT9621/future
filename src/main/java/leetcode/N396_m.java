package leetcode;

/**
 * https://leetcode-cn.com/problems/rotate-function/
 * 旋转函数
 * 给定一个长度为 n 的整数数组 nums 。
 * 假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
 * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
 * 返回 F(0), F(1), ..., F(n-1)中的最大值。生成的测试用例让答案符合 32 位 整数。
 *
 * n == nums.length  1 <= n <= 105  -100 <= nums[i] <= 100
 *
 * 思路一 暴力计算（毫无疑问超时😮‍💨）
 * 思路二 基于上一个 F 的计算
 *     4    3    2    6
 * F0  0*4  1*3  2*2  3*6  F0
 * F1  3*4  0*3  1*2  2*6  F1 = F0 - SUM(nums) + N * nums[0]
 * F2  2*4  3*3  0*2  1*6  F2 = F1 - SUM(nums) + N * nums[1]
 * F3  1*4  2*3  3*2  0*6  F3 = F2 - SUM(nums) + N * nums[2]
 *  那么 F(x) = F(x-1) - SUM(nums) + N * nums[x-1]
 */
public class N396_m {

    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (int j = 0; j < n; j++) {
                temp += ((i + j) % n) * nums[j];
            }
            max = Math.max(max, temp);
        }
        return max;
    }

    public int maxRotateFunction1(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 计算 f0
        int fx = 0;
        for (int i = 0; i < n; i++) {
            fx += nums[i] * i;
        }
        int max = fx;
        // 计算 f1 - f(n-1)
        for (int f = 1; f < n; f++) {
            fx = fx - sum + n * nums[f - 1];
            max = Math.max(fx, max);
        }
        return max;
    }

}
