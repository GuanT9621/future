package leetcode;

import java.util.Stack;

/**
 * 计算公式结果
 * 比如 3,2,1,+,-,3,4,*,-
 * 即 3-(2+1)-3*4 = -12
 *
 * 解决方法
 * 利用栈来实现，遇到数字则压入站内，遇到操作符号则弹出两个栈元素，进行计算，并将计算结果压入站内
 */
public class S_Calc {

    public static long calc(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (String s : args) {
            if (s.equals("+")) {
                Integer i1 = stack.pop();
                Integer i2 = stack.pop();
                stack.push(i2 + i1);
            } else if (s.equals("-")) {
                Integer i1 = stack.pop();
                Integer i2 = stack.pop();
                stack.push(i2 - i1);
            } else if (s.equals("*")) {
                Integer i1 = stack.pop();
                Integer i2 = stack.pop();
                stack.push(i2 * i1);
            } else if (s.equals("/")) {
                Integer i1 = stack.pop();
                Integer i2 = stack.pop();
                stack.push(i2 / i1);
            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String[] s = new String[]{"3","2","1","+","-","3","4","*","-"};
        long calc = calc(s);
        System.out.println(calc);
    }

}
