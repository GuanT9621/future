package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 寻找无重复字符的最长子串
 * <p>
 * 思路：滑动窗口
 * 其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，当再进入 a，队列变成了 abca，这时候不满足要求。
 * 所以，我们要移动这个队列！如何移动？
 * 我们只要把队列的左边的元素移出就行了，直到满足题目要求！
 * 一直维持这样的队列，找出队列出现最长的长度时候，求出解！
 * 时间复杂度：O(n)
 *
 *
 * 滑动窗口同样思路的题还有
 *
 * 3. 无重复字符的最长子串
 * 30. 串联所有单词的子串
 * 76. 最小覆盖子串
 * 159. 至多包含两个不同字符的最长子串
 * 209. 长度最小的子数组
 * 239. 滑动窗口最大值
 * 340. 至多包含 K 个不同字符的最长子串
 * 567. 字符串的排列
 * 632. 最小区间
 * 727. 最小窗口子序列
 *
 */
public class N3 {

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        int max = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i+1 - left);
        }
        System.out.println(left);
        return max;
    }

    public static void main(String[] args) {
        String s = "abcbdn";
        int i = lengthOfLongestSubstring(s);
        System.out.println(i);
    }

}
