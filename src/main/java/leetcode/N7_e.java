package leetcode;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 * 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31, 2^31− 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 思路
 * 类似于链表反转一样，
 * 1 有一个取出和放入的操作，对10取模就能取到最后一位，放入则为 模 + reverse数*10，更新 x = x/10，循环结束即 x == 0
 * 2 有符合数判断溢出：和MIN INT 或者 MAX INT 去掉一个符号位比较
 */
public class N7_e {
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            rev = x%10 + rev * 10;
            x = x/10;
        }
        return rev;
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
    }
}
