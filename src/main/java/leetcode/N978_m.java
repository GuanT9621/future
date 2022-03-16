package leetcode;

/**
 * https://leetcode-cn.com/problems/longest-turbulent-subarray/
 * 最长湍流子数组
 * 给定一个整数数组 arr ，返回 arr 的 最大湍流子数组的长度 。
 * 如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是 湍流子数组 。
 * 更正式地来说，当 arr 的子数组 A[i], A[i+1], ..., A[j] 满足仅满足下列条件时，我们称其为湍流子数组：
 * 若 i <= k < j ：
 * 或 当 k 为奇数时，A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 当 k 为偶数时，A[k] > A[k+1]，且当 k 为奇数时，A[k] < A[k+1]。
 * 条件 1 <= arr.length <= 4 * 10^4    0 <= arr[i] <= 10^9
 *
 * 思路 滑动窗口
 *
 * 思路 动态规划DP
 *
 */
public class N978_m {

    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int ans = 1;
        int left = 0;
        int right = 0;
        while (right < n - 1) {
            if (left == right) {
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
                right++;
            } else {
                if (arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) {
                    right++;
                } else if (arr[right - 1] > arr[right] && arr[right] < arr[right + 1]) {
                    right++;
                } else {
                    left = right;
                }
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        // 4,8,12,16 2
        // 9, 4, 2, 10, 7, 8, 8, 1, 9 5
        // 100 1
        // 9, 9 1
        // 0,8,45,88,48,68,28,55,17,24 8
        int i = new N978_m().maxTurbulenceSize(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9});
        System.out.println(i);
    }

}
