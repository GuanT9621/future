package leetcode;

/**
 * https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 * 阶乘后的零
 * 给定一个整数 n (0 <= n <= 10^4) ，返回 n! 结果中尾随零的数量。提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 *
 * 思路 遍历 On O1
 * 产生 0 的场景，2*5*Y，X*10*Y，X*100*Y，总结可以发现只有出现 5 的时候才有可能出现 10，那么我们计算 5 的个数即可。
 * 即 统计 [1,n] 中质因子 p 的个数。遍历每个元素，计算该元素不断除以 5，能整除的次数。
 *
 * 思路 优化 Ologn O1
 * 既然需要考虑 [1,n] 中质因子 p 的个数。
 * 那么 n / p 得到了对 p 的个数，
 * 那么 n / (p^2) 得到了对 p 的个数的2倍，
 * 以此类推可以得到全部的个数
 */
public class N172_m {

    public int trailingZeroes(int n) {
        int count = 0;
        for (int i=1; i <= n; i++) {
            int tmp = i;
            while (tmp % 5 == 0) {
                count++;
                tmp = tmp / 5;
            }
        }
        return count;
    }

    public int trailingZeroes2(int n) {
        int count = 0;
        while (n != 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    public static void main(String[] args) {
        // 5 10 15 20 25 30
        // 1 1 1 1 2 1
        int i = new N172_m().trailingZeroes(30);
        System.out.println(i);
    }

}
