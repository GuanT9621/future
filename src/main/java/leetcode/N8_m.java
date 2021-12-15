package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 *  字符串转换整数 (atoi)
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * 函数 myAtoi(string s) 的算法如下：
 *
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−2^31, 2^31− 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 2^31− 1 的整数应该被固定为 2^31− 1 。
 * 返回整数作为最终结果。
 *
 * 自动机
 * 思路
 * 字符串处理的题目往往涉及复杂的流程以及条件情况，如果直接上手写程序，一不小心就会写出极其臃肿的代码。
 * 因此，为了有条理地分析每个输入字符的处理方法，我们可以使用自动机这个概念：
 * 我们的程序在每个时刻有一个状态 s，每次从序列中输入一个字符 c，并根据字符 c 转移到下一个状态 s'。
 * 这样，我们只需要建立一个覆盖所有情况的从 s 与 c 映射到 s' 的表格即可解决题目中的问题。
 *
 * 	            ' '	        +/-	        number	    other
 * start	    start	    signed	    in_number	end
 * signed	    end	        end	        in_number	end
 * in_number	end	        end	        in_number	end
 * end	        end	        end	        end	        end
 */
public class N8_m {

    public int myAtoi(String str) {
        Automaton automaton = new Automaton();
        for (char c : str.toCharArray()) {
            automaton.get(c);
        }
        return (int) (automaton.sign * automaton.ans);
    }

    class Automaton {
        public int sign = 1; // 符号 +1 or -1
        public long ans = 0; // 无符号结果
        private String state = "start"; // 当前状态
        private final Map<String, String[]> table = new HashMap<>();

        public Automaton() {
            table.put("start",      new String[]{"start", "signed", "in_number", "end"});
            table.put("signed",     new String[]{"end",   "end",    "in_number", "end"});
            table.put("in_number",  new String[]{"end",   "end",    "in_number", "end"});
            table.put("end",        new String[]{"end",   "end",    "end",       "end"});
        }

        public void get(char c) {
            state = table.get(state)[get_col(c)];
            if ("in_number".equals(state)) {
                ans = ans * 10 + c - '0';
                ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
            } else if ("signed".equals(state)) {
                sign = c == '+' ? 1 : -1;
            }
        }

        private int get_col(char c) {
            if (c == ' ') {
                return 0;
            }
            if (c == '+' || c == '-') {
                return 1;
            }
            if (Character.isDigit(c)) {
                return 2;
            }
            return 3;
        }
    }

    public static void main(String[] args) {
        float x = 1.f;
        N8_m n8_m = new N8_m();
        int i = n8_m.myAtoi("1.3.3");
        System.out.println(i);
    }

}
