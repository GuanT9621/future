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
 * 本题主要考察的是问题分析-数学归纳
 * 先审题，一、Alice先手；二、双方采用最优解。返回true代表A赢，即不想等
 * 1、由于Alice和Bob都做最优策略，所以左右两边的问号数目可以抵消-彼此做相反策略，
 * 2、抵消后的剩余问号个数
 *      为0，直接返回答案
 *      为奇数，返回Alice赢（Alice做最后一次问号置换）
 *      为偶数，仅一侧剩余问号
 * 3、统计前后部分数字和，分为三种情况：
 *      1、问号置换为的数字可以认为只有0和9，剩余问号个数为偶数个，每两个为一个周期，lice只有两种策略（走极端）：
 *          1、增大，Alice回合置换问号为9（Bob阻止，Bob回合置换问号为0）；
 *          2、减小，Alice回合置换问号为0（Bob阻止，Bob回合置换问号为9），所以，每个周期剩余问号一侧都会增加9，如果最终两侧相等，则Bob赢，否则Alice赢。
 *      2、有剩余问号一侧总和只增不减。
 */
public class N1927_m {

    public static boolean sumGame(String num) {
        char[] chars = num.toCharArray();
        int length = chars.length;
        int half = length / 2;
        int lSum = 0, rSum = 0; // 左右侧的和
        int lx = 0, rx = 0; // 左右侧的问号个数
        for (int i=0; i<half; i++) {
            if (chars[i] == '?') {
                lx += 1;
            } else {
                lSum += (chars[i] - '0');
            }
        }
        for (int i=half; i<length; i++) {
            if (chars[i] == '?') {
                rx += 1;
            } else {
                rSum += (chars[i] - '0');
            }
        }
        int x = lx - rx;
        if (x == 0) { // 抵消后的问号个数为0
            return lSum != rSum;
        }
        if (0 != x%2) { // 抵消后的问号个数为奇数
            return true;
        }
        // 抵消后的问号个数为偶数
        if (x < 0) { // 右侧剩余偶数个问号
            if (rSum >= lSum) { // 有剩余问号一侧总和只增不减
                return true;
            } else {
                return lSum != rSum + 9 * Math.abs(x) / 2;
            }
        } else { // 左侧剩余偶数个问号
            if (lSum >= rSum) { // 有剩余问号一侧总和只增不减
                return true;
            } else {
                return rSum != lSum + 9 * Math.abs(x) / 2;
            }
        }
    }

    public static void main(String[] args) {
//        String num = "5023";
//        String num = "25??";
//        String num = "?3295???";
//        String num = "?45???";
        String num = "9?";
//        String num = "78?7?897882738374?582??2?6570?6060468336052286912245?5661003";
        boolean b = sumGame(num);
        System.out.println(b);
    }

}
