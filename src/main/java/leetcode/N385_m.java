package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/mini-parser/
 * 迷你语法分析器
 * 给定一个字符串 s 表示一个整数嵌套列表，实现一个解析它的语法分析器并返回解析的结果 NestedInteger 。
 * 列表中的每个元素只可能是整数或整数嵌套列表
 *
 * 思路 栈
 * 遍历逐个 NestedInt 入栈，遇到 ] 则出栈
 *
 * 思路 递归
 * 遇到 [ 则开始递归
 */
public class N385_m {

    class NestedInteger {
        public NestedInteger() {}
        public NestedInteger(int value) {}
        public boolean isInteger() {return false;}
        public Integer getInteger() {return null;}
        public void setInteger(int value) {}
        public void add(NestedInteger ni) {}
        public List<NestedInteger> getList() {return null;}
    }

    /**
     * 输入：s = "324",
     * 输出：324
     * 解释：你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
     * 示例 2：
     *
     * 输入：s = "[123,[456,[789]]]",
     * 输出：[123,[456,[789]]]
     * 解释：返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：
     * 1. 一个 integer 包含值 123
     * 2. 一个包含两个元素的嵌套列表：
     *     i.  一个 integer 包含值 456
     *     ii. 一个包含一个元素的嵌套列表
     *          a. 一个 integer 包含值 789
     */
    public NestedInteger deserialize(String s) {
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        Stack<NestedInteger> stack = new Stack<>();
        String tempNum = "";
        for (int i=0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                stack.push(new NestedInteger());
            } else if (c == ',' || c == ']') {
                if (!"".equals(tempNum)) {
                    stack.peek().add(new NestedInteger(Integer.parseInt(tempNum)));
                    tempNum = "";
                }
                if (c == ']') {
                    NestedInteger ni = stack.pop();
                    stack.peek().add(ni);
                }
            } else {
                tempNum += c;
            }
        }
        return stack.pop();
    }

}
