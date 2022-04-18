package leetcode;

/**
 * https://leetcode-cn.com/problems/maximize-the-confusion-of-an-exam/
 * 考试的最大困扰度
 * 一位老师正在出一场由 n 道判断题构成的考试，每道题的答案为 true （用 'T' 表示）或者 false （用 'F' 表示）。
 * 老师想增加学生对自己做出答案的不确定性，方法是 最大化 有 连续相同 结果的题数。（也就是连续出现 true 或者连续出现 false）。
 * 给你一个字符串 answerKey ，其中 answerKey[i] 是第 i 个问题的正确结果。除此以外，还给你一个整数 k ，表示你能进行以下操作的最多次数：
 * 每次操作中，将问题的正确答案改为 'T' 或者 'F' （也就是将 answerKey[i] 改为 'T' 或者 'F' ）。
 * 请你返回在不超过 k 次操作的情况下，最大 连续 'T' 或者 'F' 的数目。
 *
 * 题目简化：给一个由 T F 组成的字符串，在不超过 k 次操作的情况下，最大连续 'T' 或 'F' 的数目。
 *
 * 思路 滑动窗口
 */
public class N2024_m {

    /** 超时，需要进行优化 */
    public int maxConsecutiveAnswers(String answerKey, int k) {
        char[] chars = answerKey.toCharArray();
        int n = chars.length;
        int ans = 0;
        for (int i=0; i < n; i++) {
            int t = 0;
            int f = 0;
            for (int left = i; left < n; left++) {
                if (chars[left] == 'T') {
                    t++;
                }
                if (chars[left] == 'F') {
                    f++;
                }
                if (Math.min(t, f) <= k) {
                    ans = Math.max(ans, t + f);
                }
                if (Math.min(t, f) > k) {
                    break;
                }
            }
        }
        return ans;
    }

    public int maxConsecutiveAnswers2(String answerKey, int k) {
        return Math.max(maxConsecutiveChar(answerKey, k, 'T'), maxConsecutiveChar(answerKey, k, 'F'));
    }
    public int maxConsecutiveChar(String answerKey, int k, char ch) {
        char[] chars = answerKey.toCharArray();
        int max = 0;
        for (int left = 0, right = 0, sum = 0; right < chars.length; right++) {
            sum += chars[right] == ch ? 0 : 1;
            if (sum > k) {
                sum -= chars[left++] == ch ? 0 : 1;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        // "TTFF"
        //2
        //"TFFT"
        //1
        //"TTFTTFTT"
        //1
        int tfft = new N2024_m().maxConsecutiveAnswers2("TTFTTFTT", 1);
        System.out.println(tfft);
    }

}
