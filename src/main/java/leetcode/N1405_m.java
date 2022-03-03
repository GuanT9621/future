package leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/longest-happy-string/
 * 最长快乐字符串
 * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 * s 是一个尽可能长的快乐字符串。
 * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
 * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 *
 * 思路 模拟
 * 1 先分析什么情况不能存在这样的字符串，注意条件二，最多！ 所以肯定存在，比如 "a"
 * 2 a b c 的关系，若 c 最大， a，b 足以拆分 c 时。那么即 c = (a + b + 1) * 3
 * 3 填充即可
 */
public class N1405_m {

    public String longestDiverseString(int a, int b, int c) {
        int max = Math.max(a, Math.max(b, c));
        int newMax = (a + b + c + 1 - max) * 2;
        newMax = Math.min(max, newMax);
        int length = newMax + (a + b + c - max);
        char[] ans = new char[length];
        int p = 2;
        if (max == a) {
            for (int ia=0, ib=0, ic=0; ia+ib+ic<length; ) {

            }
            Arrays.fill(ans, 'a');
            for (int i=0; i < b; i++) {
                ans[p + 3*i] = 'b';
            }
            p = p + 3*b;
            for (int i=0; i < c; i++) {
                ans[p + 3*i] = 'c';
            }
        } else if (max  == b) {
            Arrays.fill(ans, 'b');
            for (int i=0; i < a; i++) {
                ans[p + 3*i] = 'a';
            }
            p = p + 3*a;
            for (int i=0; i < c; i++) {
                ans[p + 3*i] = 'c';
            }
        } else {
            Arrays.fill(ans, 'c');
            for (int i=0; i < b; i++) {
                ans[p + 3*i] = 'b';
            }
            p = p + 3*b;
            for (int i=0; i < a; i++) {
                ans[p + 3*i] = 'a';
            }
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        String s = new N1405_m().longestDiverseString(2, 2, 1);
        System.out.print(s);
    }

}
