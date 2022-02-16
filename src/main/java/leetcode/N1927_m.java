package leetcode;

/**
 * https://leetcode-cn.com/problems/sum-game/
 * 求和游戏
 * Alice 和 Bob 玩一个游戏，两人轮流行动，Alice 先手 。
 * 给你一个 偶数长度 的字符串 num ，每一个字符为数字字符或者 '?' 。每一次操作中，如果 num 中至少有一个 '?' ，那么玩家可以执行以下操作：
 * 选择一个下标 i 满足 num[i] == '?' 。
 * 将 num[i] 用 '0' 到 '9' 之间的一个数字字符替代。
 * 当 num 中没有 '?' 时，游戏结束。
 *
 * Bob 获胜的条件是 num 中前一半数字的和 等于 后一半数字的和。Alice 获胜的条件是前一半的和与后一半的和 不相等 。
 *
 * 思路
 * 先审题，一、Alice先手；二、双方采用最优解。返回true代表A赢，即不想等
 * 问号可以约掉吗？可以。没有问号看结果；一个问号A赢；多个问号比差值。
 */
public class N1927_m {

    public static boolean sumGame(String num) {
        char[] chars = num.toCharArray();
        int length = chars.length;
        int half = length / 2;
        int left = 0;
        int right = 0;
        int lx = 0;
        int rx = 0;
        for (int i=0; i<half; i++) {
            int j = chars[i] - '0';
            if (j > 9) {
                lx += 1;
            } else {
                left += j;
            }
        }
        for (int i=half; i<length; i++) {
            int j = chars[i] - '0';
            if (j > 9) {
                rx += 1;
            } else {
                right += j;
            }
        }
        if (lx - rx == 0) {
            return left != right;
        } else if (Math.abs(lx - rx) == 1) {
            return true;
        } else {
            int abs = Math.abs(left - right) / Math.abs(lx - rx);
            return abs <= 9;
        }
    }

    public static void main(String[] args) {
//        String num = "5023";
//        String num = "25??";
        String num = "?3295???";
//        String num = "?45???";
//        String num = "9?";
//        String num = "78?7?897882738374?582??2?6570?6060468336052286912245?5661003";
        boolean b = sumGame(num);
        System.out.println(b);
    }

}
