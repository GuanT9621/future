package leetcode;

/**
 * https://leetcode-cn.com/problems/maximum-repeating-substring/
 * 最大重复子字符串
 * 给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，那么单词 word 的 重复值为 k 。
 * 单词 word 的最大重复值是单词 word 在 sequence 中最大的重复值。如果 word 不是 sequence 的子串，那么重复值 k 为 0 。
 *
 * 给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。
 *
 * 思路一 迭代法
 * 从 sequence 里每一个字符开始匹配，如果有符合的就继续匹配，直到不符合，记录当前的最大 k
 *
 * 思路二 暴力法
 * 先查询是否包含word，每次将word * n在进行查询，直到查询出包含的最大数。
 */
public class N1668_e {

    public static int maxRepeating(String sequence, String word) {
        // todo
        char[] total = sequence.toCharArray();
        char[] target = word.toCharArray();
        int maxRpt = 0;
        for (int i=0; i<total.length; i++) {
            for (int j=0; j<target.length; j++) {
                if (i+j < total.length) {
                    if (total[i + j] == target[j]) {

                    }
                }
            }
        }
        return maxRpt;
    }

    // 暴力法
    public static int maxRepeating2(String sequence, String word) {
        int maxRpt = 0;
        String maxWord = word;
        while (sequence.contains(maxWord)) {
            maxWord += word;
            maxRpt++;
        }
        return maxRpt;
    }

    public static void main(String[] args) {
        String sequence = "aaabaaaabaaabaaaabaaaabaaaabaaaaba";
        String word = "aaaba";
        int i = maxRepeating(sequence, word);
        System.out.println(i);
    }

}
