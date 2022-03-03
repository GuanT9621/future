package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/swap-for-longest-repeated-character-substring/
 * 单字符重复子串的最大长度
 * 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
 * 给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
 * 条件 1 <= text.length <= 20000     text 仅由小写英文字母组成。
 *
 * 思路
 * 1 最长的子串有三种情况，一种是 xxx， 一种是 xxx?xxx
 * 2 判断其他位置是否还有 1 个相同字符
 * 3 尝试补全子串，判断最长子串
 */
public class N1156_m {

    public int maxRepOpt1(String text) {
        char[] chars = text.toCharArray();
        int length = text.length();
        int max = 0;

        return max;
    }

    public static void main(String[] args) {
        String text = "aaabaaa";
        int i = new N1156_m().maxRepOpt1(text);
        System.out.print(i);
    }

}
