package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/substring-with-concatenation-of-all-words/
 * 串联所有单词的子串
 * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 *
 * 1 <= s.length <= 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * s 和 words[i] 由小写英文字母组成
 *
 * 思路 滑动窗口 + 哈希表
 *
 */
public class N30_h {

    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length();
        int w = words[0].length() * words.length;
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= n - w; i++) {
            HashMap<String, Integer> copy = new HashMap<>(map);
            if (find(s, i, words, copy)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public boolean find(String s, int i, String[] words, Map<String, Integer> map) {
        for (int j = 1; j <= words.length; j++) {
            int start = i + words[0].length() * (j - 1);
            int end = i + words[0].length() * j;
            String substring = s.substring(start, end);
            Integer count = map.getOrDefault(substring, 0);
            if (count == 0) {
                return false;
            } else {
                map.put(substring, count - 1);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> list = new N30_h().findSubstring("barfoothefoobarman", new String[]{"foo", "bar"});
        list.forEach(System.out::println);
    }
}
