package leetcode;

/**
 * https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
 *  字典序的第K小数字
 * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
 *
 * 思路 字典树（前缀树）
 * 1 将数字转换为字符串，进行字典树构建
 * 2 层序遍历字典树，取第 k 个即可
 */
public class N440_h {

    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while (k > 0) {
            int steps = getSteps(curr, n);
            if (steps <= k) {
                k -= steps;
                curr++;
            } else {
                curr = curr * 10;
                k--;
            }
        }
        return curr;
    }

    public int getSteps(int curr, long n) {
        int steps = 0;
        long first = curr;
        long last = curr;
        while (first <= n) {
            steps += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        return steps;
    }

    public static void main(String[] args) {
        int kthNumber = new N440_h().findKthNumber(10, 3);
        System.out.println(kthNumber);
    }

}
