package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class S_MaxSum {

    public static List<Integer> sum(int[] input) {
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<input.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            Integer top = stack.peek();
            if (top + 1 == i) {
                if (input[i] > input[top]) {
                    stack.pop();
                    stack.push(i);
                }
            } else {
                stack.push(i);
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            res.add(input[stack.pop()]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] in = new int[]{10,1,6,100,90,2};
        List<Integer> sum = sum(in);
        for (Integer s : sum)
            System.out.println(s);
    }

}
