package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/basic-calculator-ii/
 * 基本计算器 II
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。整数除法仅保留整数部分。
 * 你可以假设给定的表达式总是有效的。所有中间结果将在 [-2^31, 2^31 - 1] 的范围内。
 * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * 条件  1 <= s.length <= 3 * 10^5
 *      s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 *      s 表示一个 有效表达式
 *      表达式中的所有整数都是非负整数，且在范围 [0, 2^31 - 1] 内
 *      题目数据保证答案是一个 32-bit 整数
 *
 * 思路 双栈
 * 遇到加减入栈，遇到乘除计算完入栈，最后累加整个栈
 * 因为存在运算符优先级，所以可以先进行所有乘除运算，然后在顺序计算。
 */
public class N227_m {

    public int calculate(String s) {
        char[] chars = s.toCharArray();
        Deque<Integer> deque = new LinkedList<>();
        int num = 0;
        char preSign = '+';
        for (int i=0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch >= '0' && ch <= '9') {
                num = num * 10 + (ch - '0');
            }
            // 不是数字，不是空格，是最后一位或+-*/
            if (((ch < '0' || ch > '9') && ' ' != ch) || i == chars.length - 1) {
                if ('+' == preSign) {
                    deque.push(num);
                } else if ('-' == preSign) {
                    deque.push(-num);
                } else if ('*' == preSign) {
                    deque.push(deque.pop() * num);
                } else if ('/' == preSign) {
                    deque.push(deque.pop() / num);
                }
                num = 0;
                preSign = ch;
            }
        }
        int ans = 0;
        while (!deque.isEmpty()) {
            ans += deque.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        int calculate = new N227_m().calculate("3+2*2+3");
        System.out.println(calculate);
    }
}
