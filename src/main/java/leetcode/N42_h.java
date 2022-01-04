package leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water/
 *  接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 思路一 双指针
 * 思路二 单调栈
 * 思路三 动态规划
 */
public class N42_h {

    // 单调栈，栈顶最小
    public static int trap2(int[] height) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                result += currWidth * currHeight;
            }
            stack.push(i);
        }
        return result;
    }

    // 双指针
    public static int trap(int[] height) {
        int result = 0;
        int l = 0, r = height.length - 1;
        int lMax = 0 , rMax = 0;
        while (l < r) {
            lMax = Math.max(lMax, height[l]);
            rMax = Math.max(rMax, height[r]);
            if (height[l] < height[r]) {
                result += lMax - height[l];
                l++;
            } else {
                result += rMax - height[r];
                r--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] height = new int[]{4,2,0,3,2,5};
        int trap = trap(height);
        System.out.println(trap);
    }

}
