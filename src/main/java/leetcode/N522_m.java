package leetcode;

/**
 * https://leetcode.cn/problems/longest-uncommon-subsequence-ii/
 * 最长特殊序列 II
 * 即 最长的无重复子串长度
 * 给定字符串列表 strs ，返回其中 最长的特殊序列 的长度。如果最长特殊序列不存在，返回 -1 。
 * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。s 的 子序列可以通过删去字符串 s 中的某些字符实现。
 * 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。
 * "aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
 * 2 <= strs.length <= 50   1 <= strs[i].length <= 10   strs[i] 只包含小写英文字母
 *
 * 思路 暴力遍历
 * 可以肯定这个 最长的无重复子串长度 如果存在，那么他的长度肯定最长（因为本身也是子序列）
 *
 *
 */
public class N522_m {

    public int findLUSlength(String[] strs) {
        String ans = "";
        int n = strs.length;
        for (int i = 0; i < n; i++) {
            // 剪枝
            if (ans.length() >= strs[i].length()) {
                continue;
            }
            if (pass(strs, i)) {
                ans = strs[i];
            }
        }
        return ans.length() == 0 ? -1 : ans.length();
    }

    private boolean pass(String[] strs, int i) {
        int n = strs.length;
        for (int j = 0; j < n; j++) {
            if (i == j) {
                continue;
            }
            // 剪枝
            if (strs[i].length() > strs[j].length()) {
                continue;
            }
            if (isSub(strs[j], strs[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isSub(String father, String sub) {
        int k = 0;
        for (char ic : sub.toCharArray()) {
            boolean find = false;
            while (k < father.length()) {
                char jc = father.charAt(k);
                k++;
                if (jc == ic) {
                    find = true;
                    break;
                }
            }
            if (!find) {
                return false;
            }
        }
        return true;
    }


}
