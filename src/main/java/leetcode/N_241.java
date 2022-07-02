package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/different-ways-to-add-parentheses/
 * 为运算表达式设计优先级
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 10^4 。
 * 条件  1 <= expression.length <= 20
 *      expression 由数字和算符 '+'、'-' 和 '*' 组成。
 *      输入表达式中的所有整数值在范围 [0, 99]
 *
 * 思路 DFS
 * 每次递归消耗一个算符，知道最后剩下一个数字。
 *
 * 思路 DFS 切分递归
 * 由于方法一在实现时比较麻烦，采用切分递归
 *
 * 思路 动态规划
 *
 */
public class N_241 {

    // 算法二 切分递归
    static class Solution1 {
        char[] cs;
        public List<Integer> diffWaysToCompute(String s) {
            cs = s.toCharArray();
            return dfs(0, cs.length - 1);
        }
        private List<Integer> dfs(int l, int r) {
            List<Integer> ans = new ArrayList<>();
            for (int i = l; i <= r; i++) {
                if ('0' <= cs[i] && cs[i] <= '9')
                    continue;
                List<Integer> l1 = dfs(l, i - 1);
                List<Integer> l2 = dfs(i + 1, r);
                for (int a : l1) {
                    for (int b : l2) {
                        if (cs[i] == '+')
                            ans.add(a + b);
                        else if (cs[i] == '-')
                            ans.add(a - b);
                        else
                            ans.add(a * b);
                    }
                }
            }
            if (ans.isEmpty()) {
                int cur = 0;
                for (int i = l; i <= r; i++)
                    cur = cur * 10 + (cs[i] - '0');
                ans.add(cur);
            }
            return ans;
        }
    }


    // 算法二 切分递归
    static class Solution2 {
        List<Integer> ops = new ArrayList<>();
        int add = -1;
        int sub = -2;
        int mcl = -3;
        public List<Integer> diffWaysToCompute(String s) {
            toOps(s);
            return dfs(0, ops.size() - 1);
        }
        private void toOps(String s) {
            int num = 0;
            for (char c : s.toCharArray()) {
                if ('0' <= c && c <= '9') {
                    num = num * 10 + (c - '0');
                } else {
                    ops.add(num);
                    num = 0;
                    if (c == '+') {
                        ops.add(add);
                    } else if (c == '-') {
                        ops.add(sub);
                    } else if (c == '*') {
                        ops.add(mcl);
                    }
                }
            }
            ops.add(num);
        }
        private List<Integer> dfs(int l, int r) {
            List<Integer> ans = new ArrayList<>();
            for (int i = l; i <= r; i++) {
                Integer num = ops.get(i);
                if (num >= 0) {
                    continue;
                }
                List<Integer> l1 = dfs(l, i - 1);
                List<Integer> l2 = dfs(i + 1, r);
                for (int a : l1) {
                    for (int b : l2) {
                        if (num == add)
                            ans.add(a + b);
                        else if (num == sub)
                            ans.add(a - b);
                        else
                            ans.add(a * b);
                    }
                }
            }
            if (ans.isEmpty()) {
                ans.add(ops.get(l));
            }
            return ans;
        }
    }

    static class Solution3 {
        // 算法一 递归消耗
        List<String> op = Arrays.asList("+", "-", "*");
        List<String> ops = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        public List<Integer> diffWaysToCompute(String s) {
            toOps(s);
            dfs(ops);
            return ans;
        }
        private void toOps(String s) {
            int num = 0;
            for (char c : s.toCharArray()) {
                if ('0' <= c && c <= '9') {
                    num = num * 10 + (c - '0');
                } else {
                    ops.add(String.valueOf(num));
                    num = 0;
                    ops.add(String.valueOf(c));
                }
            }
            ops.add(String.valueOf(num));
        }
        private void dfs(List<String> currOps) {
            if (currOps.size() == 1) {
                ans.add(Integer.valueOf(currOps.get(0)));
                return;
            }
            for (int i = 0; i < currOps.size(); i++) {
                String item = currOps.get(i);
                if (op.contains(item)) {
                    int a = Integer.parseInt(currOps.get(i - 1));
                    int b = Integer.parseInt(currOps.get(i + 1));
                    List<String> nextOps = new ArrayList<>(currOps);
                    nextOps.remove(i - 1);
                    nextOps.remove(i - 1);
                    nextOps.remove(i - 1);
                    if ("+".equals(item)) {
                        nextOps.add(i - 1, String.valueOf(a + b));
                    } else if ("-".equals(item)) {
                        nextOps.add(i - 1, String.valueOf(a - b));
                    } else {
                        nextOps.add(i - 1, String.valueOf(a * b));
                    }
                    dfs(nextOps);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> integers = new Solution3().diffWaysToCompute("2*3-4*5");
        integers.forEach(System.out::println);
    }
}
