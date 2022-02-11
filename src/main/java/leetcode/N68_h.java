package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/text-justification/
 * 文本左右对齐
 * 给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * 你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * 注意:
 * 单词指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 *
 * 思路
 * 1 先将单词和空格按行存入，得到每行保存的单词结果
 * 2 对每行进行处理两边对齐单词，从左向右扩充空格
 * 3 对最后一行进行处理左对齐，后补空格
 *
 * 后续优化
 * 将切分和扩充两步骤合为一步
 */
public class N68_h {

    public static List<String> fullJustify(String[] words, int maxWidth) {
        String[][] ss = new String[words.length][maxWidth];
        int i = 0, j = 0;
        for (String word : words) {
            int lineSize = 0;
            for (String s : ss[i]) {
                if (s != null)
                    lineSize += s.length();
            }
            if (lineSize + word.length() > maxWidth) { // new line
                i++;
                j=0;
            }
            ss[i][j] = word; // put word
            if (j+1 < maxWidth) {
                ss[i][j + 1] = " "; // put space
                j = j + 2;
            }
        }
        List<String> result = new ArrayList<>();
        for (int l=0; l<=i; l++) {
            StringBuilder sb = new StringBuilder();
            if (i == l) { // end 左对齐
                for (String s : ss[l]) {
                    if (s != null && sb.length() < maxWidth) {
                        sb.append(s);
                    }
                }
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
                result.add(sb.toString());
            } else { // 两边对齐
                int wordsSize = 0;
                int wordsNum = 0;
                for (String s : ss[l]) {
                    if (s != null && !s.equals(" ")) {
                        wordsSize += s.length();
                        wordsNum++;
                    }
                }
                if (wordsNum == 1) {
                    sb.append(ss[l][0]);
                    while (sb.length() < maxWidth) {
                        sb.append(" ");
                    }
                } else {
                    int spaceNum = maxWidth - wordsSize;
                    int i1 = spaceNum / (wordsNum - 1); // 均分
                    int i2 = spaceNum % (wordsNum - 1);
                    for (String s : ss[l]) {
                        if (s != null && !s.equals(" ")) {
                            sb.append(s);
                            if (sb.length() < maxWidth) {
                                for (int n = 0; n < i1; n++) {
                                    sb.append(" ");
                                }
                                if (i2 > 0) {
                                    sb.append(" ");
                                    i2--;
                                }
                            }
                        }
                    }
                }
                result.add(sb.toString());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words = new String[] {"What","must","be","acknowledgment","shall","be"};
        int maxWidth = 16;
        List<String> strings = fullJustify(words, maxWidth);
        for (String s: strings)
            System.out.println(s);
    }
}
