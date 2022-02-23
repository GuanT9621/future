package leetcode;

/**
 * https://leetcode-cn.com/problems/reverse-only-letters/
 * 仅仅反转字母，给你一个字符串 s ，根据下述规则反转字符串：所有非英文字母保留在原有位置。所有英文字母（小写或大写）位置反转。返回反转后的 s 。
 * 条件 1 <= s.length <= 100      s 仅由 ASCII 值在范围 [33, 122] 的字符组成     s 不含 '\"' 或 '\\'
 *
 * 思路 双指针
 */
public class N917_e {

    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int length = s.length();
        int l = 0;
        int r = length - 1;
        while (l < r) {
            char lc = chars[l];
            char rc = chars[r];
            boolean ls = (lc >= 65 && lc <= 90) || (lc >= 97 && lc <= 122);
            boolean rs = (rc >= 65 && rc <= 90) || (rc >= 97 && rc <= 122);
            if (ls && rs) { // 两边都是字母时交换，移动
                chars[l] = rc;
                chars[r] = lc;
                l++;
                r--;
            }
            if (ls && !rs) { // 左边是字母，右边不是字母时，右边左移
                r--;
            }
            if (!ls && rs) { // 右边是字母，左边不是字母时，左边右移
                l++;
            }
            if (!ls && !rs) { // 两边都不是字母时移动
                l++;
                r--;
            }
        }
        return String.valueOf(chars);
    }

}
