package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/simplified-fractions/
 * 最简分数
 * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 * 1 <= n <= 100
 *
 * 思路 数学
 * 分子分母的最大公约数为 1，则为最简分数
 * O(n^2 log n) O(1)
 */
public class N1447_m {

    public static List<String> simplifiedFractions(int n) {
        List<String> result = new ArrayList<>();
        for (int i=1; i<n; i++) {
            for (int j=2; j<=n; j++) {
                if (i == 1 || (i < j && 1 == gcd(j, i))) {
                    result.add(i + "/" + j);
                }
            }
        }
        return result;
    }

    private static int gcd(int m,int n) {
        return n == 0 ? m : gcd(n,m % n);
    }

    public static void main(String[] args) {
        List<String> strings = simplifiedFractions(3);
        for (String s : strings)
            System.out.print(s + " ");
    }
}
