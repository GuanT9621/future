package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/replace-words/
 * 单词替换
 * 在英语中，我们有一个叫做 词根(root) 的概念，可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 * 现在，给定一个由许多词根组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * 你需要输出替换之后的句子。
 *
 * 提示：
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] 仅由小写字母组成。
 * 1 <= sentence.length <= 10^6
 * sentence 仅由小写字母和空格组成。
 * sentence 中单词的总量在范围 [1, 1000] 内。
 * sentence 中每个单词的长度在范围 [1, 1000] 内。
 * sentence 中单词之间由一个空格隔开。
 * sentence 没有前导或尾随空格。
 *
 * 思路 哈希表 + 遍历
 * 思路 字典树 + 遍历
 *
 */
public class N648_m {

    class Solution {
        private int maxRoot = Integer.MIN_VALUE;
        private int minRoot = Integer.MAX_VALUE;
        private Map<String, Integer> roots = new HashMap<>();

        public String replaceWords(List<String> dictionary, String sentence) {
            loadRootInfo(dictionary);
            StringBuilder sb = new StringBuilder();
            String[] split = sentence.split(" ", -1);
            for (String s : split) {
                sb.append(" ");
                sb.append(toRoot(s));
            }
            return sb.substring(1);
        }
        private void loadRootInfo(List<String> dictionary) {
            for (String s : dictionary) {
                maxRoot = Math.max(maxRoot, s.length());
                minRoot = Math.min(minRoot, s.length());
                roots.put(s, 0);
            }
        }
        private String toRoot(String s) {
            int end = Math.min(s.length(), maxRoot);
            for (int start = minRoot; start <= end ; start++) {
                String root = s.substring(0, start);
                if (roots.containsKey(root)) {
                    return root;
                }
            }
            return s;
        }

    }

    class Solution2 {

        class Trie{
            Map<Character, Trie> children;

            public Trie() {
                children = new HashMap<>();
            }
        }

        private Trie trie = new Trie();

        public String replaceWords(List<String> dictionary, String sentence) {
            loadRootInfo(dictionary);
            StringBuilder sb = new StringBuilder();
            String[] split = sentence.split(" ", -1);
            for (String s : split) {
                sb.append(" ");
                sb.append(toRoot(s));
            }
            return sb.substring(1);
        }
        private void loadRootInfo(List<String> dictionary) {
            for (String s : dictionary) {
                Trie temp = trie;
                for (char c : s.toCharArray()) {
                    if (!temp.children.containsKey(c)) {
                        temp.children.put(c, new Trie());
                    }
                    temp = temp.children.get(c);
                }
                temp.children.put('#', new Trie());
            }
        }
        private String toRoot(String s) {
            Trie temp = trie;
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (temp.children.containsKey('#')) {
                    return sb.toString();
                }
                if (temp.children.containsKey(c)) {
                    sb.append(c);
                    temp = temp.children.get(c);
                } else {
                    break;
                }
            }
            return s;
        }

    }

    public static void main(String[] args) {
        List<String> dictionary = new ArrayList<>();
        dictionary.add("a");
        dictionary.add("aa");
        dictionary.add("aaa");
        dictionary.add("aaaa");
        String sentence =  "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa";
        String s = new N648_m().new Solution2().replaceWords(dictionary, sentence);
        System.out.println(s);
    }

}
