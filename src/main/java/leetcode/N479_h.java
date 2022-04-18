package leetcode;

/**
 * https://leetcode-cn.com/problems/largest-palindrome-product/
 * 最大回文数乘积
 * 给定一个整数 n ，返回 可表示为两个 n 位整数乘积的 最大回文整数 。因为答案可能非常大，所以返回它对 1337 取余 。
 *
 * 思路一 打表
 * 当你看到 1 <= n <= 8 时，恭喜你可以打表了！
 *
 * 思路二 计算
 * 计算回文数的前半部分，构造回文数，判断是否满足。
 *
 */
public class N479_h {

    /**
     * 打表 1 <= n <= 8
     */
    public int largestPalindrome1(int n) {
        return new int[] {9, 987,123, 597, 677, 1218, 877, 475} [n - 1];
    }

    /**
     *
     */
    public int largestPalindrome2(int n) {
        if (n == 1) {
            return 9;
        }
        int ans = 0;
        int half = (int) Math.pow(10, n) - 1;
        for (int left = half; left > 1; left--) {
            long p = left;
            // 构造回文数 p
            for (int x = left; x > 0; x /= 10) {
                p = p * 10 + x % 10;
            }
            for (long x = half; x * x >= p; --x) {
                // 整除即可
                if (p % x == 0) {
                    return  (int) (p % 1337);
                }
            }
        }
        return ans;
    }

}
