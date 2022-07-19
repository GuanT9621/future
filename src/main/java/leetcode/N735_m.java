package leetcode;

import java.util.Stack;

/**
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 *
 * 思路 栈
 *
 */
public class N735_m {

    class Solution {

        public int[] asteroidCollision(int[] asteroids) {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < asteroids.length; i++) {
                int asteroid = asteroids[i];
                if (stack.isEmpty() || stack.peek() < 0 || asteroid > 0) {
                    stack.push(asteroid);
                } else {
                    if (stack.peek() < (asteroid * -1)) {
                        stack.pop();
                        i--;
                    } else if (stack.peek() == (asteroid * -1)) {
                        stack.pop();
                    }
                }
            }
            int[] ans = new int[stack.size()];
            int i = stack.size() - 1;
            while (!stack.isEmpty()) {
                ans[i] = stack.pop();
                i--;
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        int[] ints = new N735_m().new Solution().asteroidCollision(new int[]{-2, -2, 1, -2});
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

}
