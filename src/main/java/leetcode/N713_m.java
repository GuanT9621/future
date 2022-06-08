package leetcode;

/**
 * https://leetcode-cn.com/problems/subarray-product-less-than-k/
 * 乘积小于 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 * 1 <= nums.length <= 3 * 10^4      1 <= nums[i] <= 1000        0 <= k <= 10^6
 *
 * 思路 滑动窗口
 * 看到连续子数组就想到了滑动窗口
 * 如果一个最大窗口 m 内符合小于 k，那么 m！都符合。
 *
 *
 */
public class N713_m {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // 找到起始点
        int left = 0;
        int product = 0;
        for (; left < nums.length; left++) {
            if (nums[left] < k) {
                product = nums[left];
                break;
            }
        }
        if (product == 0) {
            return 0;
        }
        int ans = 1;
        for (int right = left + 1; right < nums.length; right++) {
            product = product * nums[right];
            if (nums[right] < k) {
                ans++;
                while (product >= k && left < right) {
                    product /= nums[left];
                    left++;
                }
                ans = ans + right - left;
            }
        }
        return ans;
    }

   /** 官方答案 */
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        int n = nums.length, ans = 0;
        int prod = 1, i = 0;
        for (int j = 0; j < n; j++) {
            prod *= nums[j];
            while (i <= j && prod >= k) {
                prod /= nums[i];
                i++;
            }
            ans += j - i + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        // [100,2,3,4,100,5,6,7,100] 11

        int i = new N713_m().numSubarrayProductLessThanK(new int[]{100, 2, 3, 4, 100, 5, 6, 7, 100}, 100);
        System.out.println(i);
    }

}
