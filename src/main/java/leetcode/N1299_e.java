package leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/replace-elements-with-greatest-element-on-right-side/
 * 和 S_firstMax 有异曲同工之妙
 *
 * 思路一：单调栈，即栈内元素保持一定单调性（单调递增或单调递减）的栈
 * 思路二：从右向左排查，每次记录最大的数字
 *
 */
public class N1299_e {

    public static int[] replaceElements2(int[] arr) {
        int rightMax = arr[arr.length - 1];
        arr[arr.length - 1] = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            int t = arr[i];
            arr[i] = rightMax;
            if (t > rightMax)
                rightMax = t;
        }
        return arr;
    }

    public static int[] replaceElements(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        if (arr.length-1 > 0) {
            stack.push(arr.length-1);
        }
        int index = arr.length-1;
        while (index > 0) {
            if (arr[index] > arr[stack.peek()]) {
                stack.push(index);
            }
            index--;
        }
        for (int i=0; i<arr.length; i++) {
            if (stack.empty()) {
                result[i] = -1;
            } else if (i < stack.peek()) {
                result[i] = arr[stack.peek()];
                if (i + 1 ==  stack.peek()) {
                    stack.pop();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{17,18,5,4,6,1};
        int[] ints = replaceElements(arr);
        for (int i : ints) {
            System.out.print(i + " ");
        }
        // 输出：[18,6,6,6,1,-1]
    }
}
