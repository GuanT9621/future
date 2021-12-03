package leetcode;

/**
 * https://leetcode-cn.com/problems/longest-common-prefix/
 * 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""
 *
 * 这题很简单，思路，依次取，不断地计算公共前缀，然后存入一个变量里
 */
public class N14_e {

    public static String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for (String str : strs) {
            prefix = longestCommonPrefix(prefix, str);
        }
        return prefix;
    }

    public static String longestCommonPrefix(String s1, String s2) {
        int length = Math.min(s1.length(), s2.length());
        StringBuilder sb = new StringBuilder();
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        for (int i=0; i<length; i++) {
            if (cs1[i] == cs2[i]) {
                sb.append(cs1[i]);
            } else {
                return sb.toString();
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        String prefix = longestCommonPrefix(strs);
        System.out.println(prefix);
    }

}
