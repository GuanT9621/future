package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * 最长回文子串，“回文串”是一个正读和反读都一样的字符串，比如“level”或者“noon”等等就是回文串。
 * 注意，回文串有两种情况 "level" 这种中间的，"noon" 这种两边的
 * 思路 1 我们知道回文串一定是对称的，所以我们可以每次循环选择一个中心，进行左右扩展，判断左右字符是否相等即可
 *
 */
public class N5_m {
    public static String longestPalindrome(String s) {
        if (s.length() == 0) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 计算 noon 形式的
            int len1 = expandAroundCenter(s, i, i);
            // 计算 level 形式的
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int l = left, r = right;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    public static void main(String[] args) {
        String s = longestPalindrome("aba");
        System.out.println(s);
    }
}
