package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/unique-substrings-in-wraparound-string/
 * 环绕字符串中唯一的子字符串
 * 把字符串 s 看作是 “abcdefghijklmnopqrstuvwxyz” 的无限环绕字符串。
 * 现在给定另一个字符串 p 。返回 s 中 唯一 的 p 的 非空子串 的数量 。1 <= p.length <= 10^5
 *
 * 思路 求结尾子串最长长度
 * 求最长子串：同一个字符结尾的子串，长的那个子串必然包含短的那个。例如 abcd 和 bcd 均以 d 结尾，bcd 是 abcd 的子串
 *
 */
public class N467_m {

    public int findSubstringInWraproundString(String p) {
        int[] ans = new int[26];
        int n = p.length();
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) {
                k++;
            } else {
                k = 1;
            }
            ans[p.charAt(i) - 'a'] = Math.max(ans[p.charAt(i) - 'a'], k);
        }
        return Arrays.stream(ans).sum();
    }

}
