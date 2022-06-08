package leetcode;

/**
 * https://leetcode.cn/problems/divide-two-integers/
 * 两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * tips 被除数和除数均为 32 位有符号整数。除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31, 2^31− 1]。本题中，如果除法结果溢出，则返回 2^31− 1。
 *
 * 思路 减法？
 *
 */
public class N29_m {

    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor < 0 && dividend < divisor) {
            return 0;
        }
        if (dividend > 0 && dividend < divisor) {
            return 0;
        }
        return 1;
    }

}
