package leetcode;

/**
 * https://leetcode-cn.com/problems/count-numbers-with-unique-digits/
 * 统计各位数字都不同的数字个数
 * 给你一个整数 n （0 <= n <= 8），统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n 。
 *
 * 思路 模拟
 * 1 分别计算几位数时对应的不同的数字个数，如 n = 4
 *      4 位时 9 x 9 x 8 x 7，因为第一位不为0
 *      3 位时 9 x 9 x 8，因为第一位不为0
 *      2 位时 9 x 9，因为第一位不为0
 *      1 位时 10
 * 2 相加即可
 */
public class N375_m {

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int[] count = new int[] {10, 9*9, 9*9*8, 9*9*8*7, 9*9*8*7*6, 9*9*8*7*6*5, 9*9*8*7*6*5*4, 9*9*8*7*6*5*4*3};
        int ans = 0;
        for (int i=0; i < n; i++) {
            ans += count[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        // 739
        int i = new N375_m().countNumbersWithUniqueDigits(3);
        System.out.println(i);
    }

}
