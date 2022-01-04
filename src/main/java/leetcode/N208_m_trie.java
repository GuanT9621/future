package leetcode;

/**
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 */
public class N208_m_trie {

    char value; // 词
    int frequency; // frequency 表示以当前单词结尾的单词数量。
    private final N208_m_trie[] children;

    public N208_m_trie() {
         this(' ');
    }

    public N208_m_trie(char c) {
        value = c;
        frequency = 0;
        children = new N208_m_trie[26];
    }

    // 返回频次
    public int insert(String word) {
        int result = 0;
        N208_m_trie node = this;
        for (int i=0; i<word.length(); i++) {
            char c = word.toCharArray()[i];
            int index = c - 'a';
            // 叶节点
            if (null == node.children[index]) {
                node.children[index] = new N208_m_trie(c);
            }
            // 到达低端
            if (i == word.length() - 1) {
                result = ++node.children[index].frequency;
            }
            node = node.children[index];
        }
        return result;
    }

    public boolean search(String word) {
        N208_m_trie node = this;
        for (int i=0; i<word.length(); i++) {
            char c = word.toCharArray()[i];
            int index = c - 'a';
            if (null == node.children[index]) {
                return false;
            }
            if (i == word.length() - 1 && 0 == node.children[index].frequency) {
                 return false;
            }
            node = node.children[index];
        }
        return true;
    }

    public boolean startsWith(String prefix) {
        N208_m_trie node = this;
        for (int i=0; i<prefix.length(); i++) {
            char c = prefix.toCharArray()[i];
            int index = c - 'a';
            if (null == node.children[index]) {
                return false;
            }
            node = node.children[index];
        }
        return true;
    }

    public static void main(String[] args) {
        N208_m_trie trie = new N208_m_trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 True
        System.out.println(trie.search("app"));     // 返回 False
        System.out.println(trie.startsWith("app"));       // 返回 True

        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 True
    }

}
