package leetcode;

/**
 * https://leetcode-cn.com/problems/interleaving-string/
 * 交错字符串
 * 给定三个字符串s1、s2、s3，请你帮忙验证s3是否是由s1和s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 *
 * 思路
 * 双指针是不可以的，因为无法解决 s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac" 的判断
 * 动态规划：f(i,j) 表示 s1 的前 i 个元素和 s2 的前 j 个元素是否能交错组成 s3 的前 i+j 个元素
 * 那么如果 s1[i-1] == s3[i+j-1]，那么取决f(i-1,j)；
 * 那么如果 s2[j-1] == s3[i+j-1]，那么取决f(i,j-1)；
 *
 * 动态规划转移方程：
 *  f(i,j) = [f(i-1,j) && s1(i-1) = s3(i+j−1)] or [f(i,j-1) && s2(j-1) = s3(i+j−1)]
 *
 * 边界为 f(0,0) = true
 */
public class N97_m {

    public static boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();
        if (l1 + l2 != l3)
            return false;
        boolean[][] f = new boolean[l1 + 1][l2 + 1];
        f[0][0] = true;
        for (int i = 0; i <= l1; ++i) {
            for (int j = 0; j <= l2; ++j) {
                if (i > 0)
                    f[i][j] = (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
                if (j > 0)
                    f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return f[l1][l2];
    }

    public static void main(String[] args) {
       String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        boolean interleave = isInterleave(s1, s2, s3);
        System.out.print(interleave);
    }

}
