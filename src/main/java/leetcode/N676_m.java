package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。
 * 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
 * 实现 MagicDictionary 类：
 * MagicDictionary() 初始化对象
 * void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
 * bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
 *
 * 思路 暴力尝试 + 长度判断优化 + 及时终止
 * 思路 暴力尝试 + 字典树
 * 思路 预处理 + 哈希 （占用空间大）
 *
 * 1 构建后不再变更仅查询
 * 2 判断两个单词是否只有一字母不同
 *
 */
public class N676_m {

    class MagicDictionary {
        String[] dictionary;

        public MagicDictionary() {}

        public void buildDict(String[] dictionary) {
            this.dictionary = dictionary;
        }

        public boolean search(String searchWord) {
            for (String s : dictionary) {
                if (s.length() == searchWord.length()) {
                    int diff = 0;
                    for (int i = 0; i < searchWord.length(); i++) {
                        if (s.charAt(i) != searchWord.charAt(i)) {
                            diff++;
                        }
                        if (diff > 1) {
                            break;
                        }
                    }
                    if (diff == 1) {
                        return true;
                    }
                }
            }
            return false;
        }

    }

    public static void main(String[] args) {
        //[[], [["hello","hallo","leetcode","judge"]], ["hello"], ["hallo"], ["hell"], ["leetcodd"], ["judge"]]
        MagicDictionary magicDictionary = new N676_m().new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello","hallo","leetcode","judge"});
        System.out.println(magicDictionary.search("judge"));
    }
}
