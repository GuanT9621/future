package leetcode;

import java.util.Arrays;
import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/longest-word-in-dictionary/
 * 词典中最长的单词
 * 给出一个字符串数组 words 组成的一本英语词典。返回 words 中最长的一个单词，该单词是由 words 词典中其他单词逐步添加一个字母组成。
 * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
 *
 * 思路 HashMap/HashSet
 * 1 排序 words
 * 2 遍历 words
 * 3 将 words 放入 hash set
 * O(\sum_{0 \le i < n} l_i \times \log n)O(∑
 * 0≤i<n
 * ​
 *  l
 * i
 * ​
 *  ×logn)
 *
 * 思路 字典树
 * 1 构造一个字典树
 * 2 按照 dfs 取最深最先的节点
 */
public class N720_e {

    public String longestWord(String[] words) {
        HashSet<String> set = new HashSet<>();
        Arrays.sort(words);
        String max = "";
        set.add(max);
        for (String word : words) {
            if (set.contains(word.substring(0, word.length() - 1))) {
                set.add(word);
                if (max.length() < word.length()) {
                    max = word;
                }
            }
        }
        return max;
    }

    class Node {
        char c;
        boolean y;
        Node[] childrens;

        public Node(char c) {
            this.c = c;
            this.y = false;
            this.childrens = new Node[26];
        }

        public void insert(String s) {
            Node tmp = this;
            char[] chars = s.toCharArray();
            for (int i=0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (tmp.childrens[index] == null) {
                    tmp.childrens[index] = new Node(chars[i]);
                }
                if (i == chars.length - 1) {
                    tmp.childrens[index].y = true;
                }
                tmp = tmp.childrens[index];
            }
        }
    }

    String max = "";
    StringBuilder sb = new StringBuilder();

    public String longestWord2(String[] words) {
        Node root = new Node('-');
        root.y = true;
        for (String word : words) {
            root.insert(word);
        }
        dfs(root);
        return max;
    }

    private void dfs(Node node) {
        if (node == null) {
            return;
        }
        if (!node.y) {
            if (max.length() < sb.length()) {
                max = sb.toString();
            }
            sb = new StringBuilder();
        } else {
            sb.append(node.c);
            for (Node children : node.childrens) {
                dfs(children);
            }
        }
    }

    public static void main(String[] args) {
        // yodn
        String[] words = new String[] {"yo","ew","fc","zrc","yodn","fcm","qm","qmo","fcmz","z","ewq","yod","ewqz","y"};
        String s = new N720_e().longestWord2(words);
        System.out.println(s);
    }
}
