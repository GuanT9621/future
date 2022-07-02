package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game/
 * 找出游戏的获胜者
 * 共有 n 名小伙伴一起做游戏。小伙伴们围成一圈，按 顺时针顺序 从 1 到 n 编号。
 * 游戏遵循如下规则：
 * 从第 1 名小伙伴所在位置 开始 。
 * 沿着顺时针方向数 k 名小伙伴，计数时需要 包含 起始时的那位小伙伴。逐个绕圈进行计数，一些小伙伴可能会被数过不止一次。
 * 你数到的最后一名小伙伴需要离开圈子，并视作输掉游戏。
 * 如果圈子中仍然有不止一名小伙伴，从刚刚输掉的小伙伴的 顺时针下一位 小伙伴 开始，回到步骤 2 继续执行。
 * 否则，圈子中最后一名小伙伴赢得游戏。
 * 给你参与游戏的小伙伴总数 n ，和一个整数 k ，返回游戏的获胜者。
 * 1 <= k <= n <= 500
 *
 * 约瑟夫环问题
 *
 * 思路 模拟 队列
 *
 * 思路 数学 f(N, K) = (f(N − 1, K) + K) % N
 *
 * 当 k = 2 时
 * 偶数 n 时，x 在上一轮位置为 2x - 1 那么 f(2n) = 2f(n) - 1
 * 奇数 n 时，x 在上一轮位置为 2x + 1 那么 f(2n + 1) = 2f(n) + 1
 * 其中 设 f(n) 为一开始有 n 个人时，生还者的位置
 */
public class N1823_m {

    public int findTheWinner1(int n, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }
        while (queue.size() > 1) {
            for (int i = 1; i < k; i++) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }
        return queue.peek();
    }

    public int findTheWinner2(int n, int k) {
        if (n == 1) {
            return 1;
        }
        return (findTheWinner2(n - 1, k) + k - 1) % n + 1;
    }

    public int findTheWinner3(int n, int k) {
        int winner = 0;
        for (int i = 2; i <= n; i++) {
            winner = (k + winner ) % i ;
        }
        return winner;
    }

    public static void main(String[] args) {
        int theWinner = new N1823_m().findTheWinner3(5, 2);
        System.out.println(theWinner);
    }
}
