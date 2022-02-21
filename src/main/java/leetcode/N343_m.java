package leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/integer-break/
 * 整数拆分
 * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。返回 你可以获得的最大乘积 。
 * 2 <= n <= 58
 *
 * 思路 迭代
 * 从分成2个开始尝试，直到分成 n/2，拆分后的数字尽可能想近，因为你可以发现 x*x 绝对大于 (x-1) * (x+1)
 *
 * 思路 数学分析
 * 出现的数字一定时 1、2、3、4；因为比他们大的数字都能拆分成两个数，如 5 < 2 * 3
 * 排除 1，因为 * 1 不会变大，但是另一个数字变小了。
 * 排除 4 因为 2 * 2 = 4；
 * 所以只能出现 2 和 3，我们希望尽可能出现3，然后补全2。
 */
public class N343_m {

    public int integerBreak(int n) {
        int max = Integer.MIN_VALUE;
        for (int k=1; k<=n/2; k++) {
            int[] breaks = breaks(n, k+1);
            int curr = 1;
            for (int b : breaks) {
                curr *= b;
            }
            max = Math.max(max, curr);
        }
        return max;
    }

    private int[] breaks(int n, int k) {
        int part = n / k;
        int other = n % k;
        int[] breaks = new int[k];
        Arrays.fill(breaks, part);
        for (int i=0; i<other; i++) {
            breaks[i] += 1;
        }
        return breaks;
    }

    public int integerBreak2(int n) {
        if (n <= 3) {
            return n-1;
        }
        int part = n / 3;
        int other = n % 3;
        if (other == 0) {
            return (int) Math.pow(3, part);
        } else if (other == 1) {
            return (int) Math.pow(3, part-1) * 4;
        } else {
            return (int) Math.pow(3, part) * 2;
        }
    }

    public static void main(String[] args) {
        int i = new N343_m().integerBreak(2);
        System.out.println(i);
    }

}
