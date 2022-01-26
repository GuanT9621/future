package leetcode;

/**
 * https://leetcode-cn.com/problems/palindrome-number/
 * 回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 *
 * 方法一 将数字反转即可，每次将数字对10取余，将新数乘10并加上余数，最后判断新数等于旧数。
 * 方法二 优化方法一，只需要反转一半即可。方法一反转后的数字大于 int.MAX，我们将遇到整数溢出问题
 */
public class N9_e {

    public static boolean isPalindrome(int x) {
        // 当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，则其第一位数字也应该是 0，只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;
        int r = 0;
        int tmp = x;
        while (tmp != 0) {
            r = r * 10 + tmp % 10; // 可能整数溢出问题
            tmp = tmp / 10;
        }
        return  r == x;
    }

    public static boolean isPalindrome2(int x) {
        // 当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，则其第一位数字也应该是 0，只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;
        int r = 0;
        while (x > r) {
            r = r * 10 + x % 10;
            x = x / 10;
        }
        return x == r || x == r/10;
    }

    public static void main(String[] args) {
        int x = 10;
        boolean palindrome = isPalindrome2(x);
        System.out.println(palindrome);
    }

}
