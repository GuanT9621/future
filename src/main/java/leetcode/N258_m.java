package leetcode;

/**
 * https://leetcode-cn.com/problems/add-digits/
 * 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 * 0 <= num <= 2^31 - 1， 要求 O(1) 时间复杂度
 *
 * 思路
 * 既然要求 O(1) 时间，那么不能采用递归的方式。
 * 考虑返回的结果只能是 0-9，其实各位相加就是数根的概念：https://en.wikipedia.org/wiki/Digital_root
 * 即数字不断的除以9，取最后的余数。
 *
 */
public class N258_m {

    public static int addDigits(int num) {
        if (num <= 9)
            return num;
        int ans = num % 9;
        if (ans == 0)
            return 9;
        return ans;
    }

    public static void main(String[] args) {
        int i = addDigits(0);
        System.out.println(i);
    }
}
