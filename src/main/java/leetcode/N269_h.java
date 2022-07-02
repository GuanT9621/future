package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/Jf1JuT/
 * 现有一种使用英语字母的外星文语言，这门语言的字母顺序与英语顺序不同。
 * 给定一个字符串列表 words ，作为这门语言的词典，words 中的字符串已经 按这门新语言的字母顺序进行了排序 。
 * 请你根据该词典还原出此语言中已知的字母顺序，并 按字母递增顺序 排列。若不存在合法字母顺序，返回 "" 。若存在多种可能的合法字母顺序，返回其中 任意一种 顺序即可。
 *
 * 字符串 s 字典顺序小于 字符串 t 有两种情况：
 * 在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。
 * 如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
 *
 * 思路 先比较首字母，然后在相同首字母里比较次字母，以此类推
 *
 * 思路 拓扑排序 + DFS/BFS
 *
 */
public class N269_h {

    public String alienOrder(String[] words) {
        StringBuilder sb = new StringBuilder();
        Set<Character> onceIn = new HashSet<>();
        int maxSize = 0;
        for (String word : words) {
            maxSize = Math.max(maxSize, word.length());
        }
        for (int i = 0; i < maxSize; i++) {
            Character prev = null;
            for (String word : words) {
                if (i < word.length()) {
                    char c = word.charAt(i);
                    if (prev != null && (onceIn.contains(c) && c != prev)) {
                        return "";
                    } else {
                        onceIn.add(c);

                        if (sb.indexOf("") != -1) {
                            sb.append(c);
                        }
                    }
                    prev = c;
                }
            }
            onceIn.clear();
        }
        return sb.toString();
    }

}
