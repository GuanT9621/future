package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
 *
 * 实现 WordFilter 类：
 * WordFilter(string[] words) 使用词典中的单词 words 初始化对象。
 * f(string pref, string suff) 返回词典中具有前缀 prefix 和后缀 suff 的单词的下标。
 * 如果存在不止一个满足要求的下标，返回其中 最大的下标 。如果不存在这样的单词，返回 -1 。
 * 1 <= words.length <= 10^4
 * 1 <= words[i].length <= 7
 * 1 <= pref.length, suff.length <= 7
 * words[i]、pref 和 suff 仅由小写英文字母组成
 * 最多对函数 f 执行 10^4 次调用
 *
 * 思路 暴力
 * 思路 字典树
 * 如 apple 依次保存 ae pl pp lp ea。 注意处理 前缀 appl 后缀 e 的情况，这时候会查询 p# p# l#
 * 难点在于初始化时处理 p# #p 这种情况。
 * ae /pl/p#/#l/
 * a# p# p# l# e#
 * #e #l #p #p #a
 *
 */
public class N745_h {

    class WordFilter {

        class Node {
            int index = -1;
            Map<String, Node> nodeMap = new HashMap<>();
        }

        Node root = new Node();

        public WordFilter(String[] words) {
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                int m = word.length();
                Node cur = root;
                for (int j = 0; j < m; j++) {
                    Node tmp = cur;
                    for (int k = j; k < m; k++) {
                        String key = word.charAt(k) + "#";
                        if (!tmp.nodeMap.containsKey(key)) {
                            tmp.nodeMap.put(key, new Node());
                        }
                        tmp = tmp.nodeMap.get(key);
                        tmp.index = i;
                    }
                    tmp = cur;
                    for (int k = j; k < m; k++) {
                        String key = "#" + word.charAt(m - 1 - k);
                        if (!tmp.nodeMap.containsKey(key)) {
                            tmp.nodeMap.put(key, new Node());
                        }
                        tmp = tmp.nodeMap.get(key);
                        tmp.index = i;
                    }
                    String key = "" + word.charAt(j) + word.charAt(m - 1 - j);
                    if (!cur.nodeMap.containsKey(key)) {
                        cur.nodeMap.put(key, new Node());
                    }
                    cur = cur.nodeMap.get(key);
                    cur.index = i;
                }
            }
        }

        public int f(String pref, String suff) {
            int n = Math.max(pref.length(), suff.length());
            Node cur = root;
            for (int i = 0; i < n; i++) {
                char a1  = i < pref.length() ? pref.charAt(i) : '#';
                char a2  = i < suff.length() ? suff.charAt(suff.length() - 1 - i) : '#';
                String key = "" + a1 + a2;
                if (!cur.nodeMap.containsKey(key)) {
                    return -1;
                }
                cur = cur.nodeMap.get(key);
            }
            return cur.index;
        }
    }

    public static void main(String[] args) {
//        String[] words = new String[] {"apple", "akkle", "ae"};
        String[] words = new String[] {"aceg", "aceaceg"};
        WordFilter wordFilter = new N745_h().new WordFilter(words);
        int f = wordFilter.f("ace", "aceg");
        System.out.println(f);
    }

}
