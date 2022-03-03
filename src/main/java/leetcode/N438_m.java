package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 * 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * 条件 1 <= s.length, p.length <= 3 * 10^4    s 和 p 仅包含小写字母
 *
 * 思路 滑动窗口
 * 1 从头开始一个一个迭代，直到 s.length - p.length + 1； 维护一个宽度为 p 的滑动窗口
 * 2 一个元素匹配到了在 p 中，判断是否符合，然后记录迭代的位置。
 *
 * 注意不要将迭代的位置直接移到滑动窗口的结尾，因为有可能当前的元素也在下一个区间内使用。
 * 如何判断窗口匹配：数组记录字母出现的次数 + Arrays.equals() ；
 *              不能用异或结果为0来判断，因为是有可能存在 aaabc 和 abccc 异或的结果也是0。
 */
public class N438_m {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        char[] scs = s.toCharArray();
        char[] pcs = p.toCharArray();
        int sl = s.length();
        int pl = p.length();
        for (int i = 0; i < sl - pl + 1; i++) {
            if (p.contains(String.valueOf(scs[i]))) {
                char[] range = Arrays.copyOfRange(scs, i, i + pl);
                int[] sCount = new int[26];
                int[] pCount = new int[26];
                for (int n=0; n < pl; n++) {
                    ++sCount[range[n] - 'a'];
                    ++pCount[pcs[n] - 'a'];
                }
                if (Arrays.equals(sCount, pCount)) {
                    ans.add(i);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        List<Integer> anagrams = new N438_m().findAnagrams(s, p);
        for (Integer anagram : anagrams) {
            System.out.println(anagram);
        }
    }
}
