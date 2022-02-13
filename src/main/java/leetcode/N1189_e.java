package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/maximum-number-of-balloons/
 * “气球” 的最大数量
 * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
 *
 * 思路 统计法
 * 记录 balon 这5个单词出现的次数，求出最少的出现次数。
 */
public class N1189_e {

    public static int maxNumberOfBalloons(String text) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('b', 0);
        map.put('a', 0);
        map.put('l', 0);
        map.put('o', 0);
        map.put('n', 0);
        for (Character c : text.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            }
        }
        int max = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            int curr = e.getKey() == 'l' || e.getKey() == 'o' ? e.getValue() /2 : e.getValue();
            max = Math.min(max, curr);
        }
        return max;
    }

    public static void main(String[] args) {
        String text = "loonbalxballpoon";
        int i = maxNumberOfBalloons(text);
        System.out.println(i);
    }
}
