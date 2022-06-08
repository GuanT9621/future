package leetcode;

/**
 * https://leetcode.cn/problems/powx-n/
 * Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n ）。
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31 - 1
 * -10^4 <= x^n <= 10^4
 *
 * 思路 快速幂计算
 * 当我们要计算 x^n 时，我们可以先递归地计算出 y = x ^ n/2
 * 根据递归计算的结果，如果 n 为偶数，那么 x^n = y^2；如果 n 为奇数，那么 x^n = y^2 * x；
 * 递归的边界为 n = 0，任意数的 0 次方均为 1。
 *
 * 思路 每个二进制数位都有一个权值，权值如下图所示，最终结果就等于所有二进制位为1的权值之积，
 * 例如 x^77 次方对应的二进制 (1001101) 和每个二进制位的权值如下
 * 1    0    0    1   1   0   1
 * x^64 x^32 x^16 x^8 x^4 x^2 x^1
 * 最终结果就是所有二进制位为1的权值之积：x^1 * x^4 * x^8 * x^64 = x^77
 *
 * 注意 负次方 = 正次方的倒数
 * 注意 小心数字溢出
 */
public class N50_m {

    class Solution {
        public double myPow(double x, int n) {
            return (long) n >= 0 ? quickMul(x, n) : 1.0 / quickMul(x, -(long) n);
        }
        public double quickMul(double x, long n) {
            if (n == 0) {
                return 1.0;
            }
            double y = quickMul(x, n / 2);
            return n % 2 == 0 ? y * y : y * y * x;
        }
    }

    public double myPow(double x, int n) {
        return (long) n >= 0 ? quickMul(x, n) : 1.0 / quickMul(x, -(long) n);
    }
    public double quickMul(double x, long n) {
        double ans = 1.0;
        double y = x;
        while (n > 0) {
            if (n % 2 == 1) {
                ans *= y;
            }
            y *= y;
            n /= 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        double v = new N50_m().myPow(2.00000, -2147483648);
        System.out.println(v);
    }

}
