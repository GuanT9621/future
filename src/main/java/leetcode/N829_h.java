package leetcode;

/**
 * https://leetcode.cn/problems/consecutive-numbers-sum/
 * 连续整数求和
 * 给定一个正整数 n，返回 连续正整数满足所有数字之和为 n 的组数 。1 <= n <= 10^9
 *
 * 思路
 * 如果正整数 n 可以表示成 k 个连续正整数之和，则由于 k 个连续正整数之和的最小值是 k(k+1)/2, 因此有 n >= k(k+1)/2, 即 2n >= k(k+1)
 * 如果正整数 n 可以表示成 k 个连续正整数之和，假设这 k 个连续正整数中的最小正整数是 x，最大正整数是 y，则有 y=x+k−1，
 * 根据等差数列求和公式有
 * n = k(x+y)/2 = k(2x+k−1)/2
 * x = n/k - (k-1)/2
 * 根据 k(k+1)≤2n 可知 x>0。分别考虑 k 是奇数和偶数的情况。
 *
 * 如果 k 是奇数，则当 n 可以被 k 整除时，正整数 n 可以表示成 k 个连续正整数之和；
 * 如果 k 是偶数，则当 n 不可以被 k 整除且 2n 可以被 k 整除时，正整数 n 可以表示成 k 个连续正整数之和。
 *
 *
 * tips 等差数列前n项和 Sn=n*a1 + n(n-1)d/2
 */
public class N829_h {

    public int consecutiveNumbersSum(int n) {
        int ans = 0;
        int bound = 2 * n;
        for (int k = 1; k * (k + 1) <= bound; k++) {
            if (isKConsecutive(n, k)) {
                ans++;
            }
        }
        return ans;
    }

    public boolean isKConsecutive(int n, int k) {
        if (k % 2 == 1) {
            return n % k == 0;
        } else {
            return n % k != 0 && 2 * n % k == 0;
        }
    }

    public int consecutiveNumbersSum1(int n) {
        if (n == 1) {
            return 1;
        }
        int ans = 1;
        int half = n / 2 + (n % 2 == 0 ? 0 : 1);
        for (int i = 1; i <= half; i++) {
            int sum = 0;
            for (int j = i; j <= half; j++) {
                sum += j;
                if (sum == n) {
                    ans++;
                    break;
                }
                if (sum > n) {
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = new N829_h().consecutiveNumbersSum(15);
        System.out.println(i);
    }

}
