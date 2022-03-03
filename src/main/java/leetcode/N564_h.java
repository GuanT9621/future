package leetcode;

/**
 * https://leetcode-cn.com/problems/find-the-closest-palindrome/
 * 寻找最近的回文数
 * 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。“最近的”定义为两个整数差的绝对值最小。
 * 条件 1 <= n.length <= 18   n 只由数字组成    n 不含前导 0    n 代表在 [1, 10^18 - 1] 范围内的整数
 *
 * 思路
 * 1 取前一半字符串
 * 2 将前一半字符串逆序
 * 3 将逆序结果补充到前一半字符串
 *
 * 需要处理的特殊情况：
 * 1 小于10的 直接返回 n - 1
 * 2 10 11 返回 9
 * 3 本身就是回文整数的，将前半截数字减一
 * 4 将前半截数字减一，或者反转前半截，哪个差绝对值最小用哪个方案。
 * 5 将前半截数字加一，或者反转前半截，哪个差绝对值最小用哪个方案。
 * 6 注意在加一减一后的位数变化，比如前半截减以后位数减少了一位，那么意味着整个数位数少了两位，
 *      其实我们是期望总体位数减少一的，这种情况需要特殊处理，将前半截位数加一后重新计算即可。
 *
 * 用原数的前半部分替换后半部分得到的回文整数。
 * 用原数的前半部分加一后的结果替换后半部分得到的回文整数。
 * 用原数的前半部分减一后的结果替换后半部分得到的回文整数。
 * 为防止位数变化导致构造的回文整数错误，因此直接构造 999…999 和 100…001 作为备选答案。
 */
public class N564_h {

    public String nearestPalindromic(String n) {
        int length = n.length();
        // 小于10的 直接返回 n - 1
        if (length == 1) {
            return String.valueOf(Integer.parseInt(n) - 1);
        }
        // 11 10 返回 9
        if (length == 2 && (Integer.parseInt(n) == 11 || Integer.parseInt(n) == 10)) {
            return String.valueOf(9);
        }
        int preHalf = Integer.parseInt(n.substring(0, length / 2 + length % 2));
        String endSub = n.substring(length / 2, length);
        StringBuilder tailHalfSb = new StringBuilder();
        char[] endChars = endSub.toCharArray();
        for (int i=endChars.length - 1; i >= 0; i--) {
            tailHalfSb.append(endChars[i]);
        }
        int tailHalf = Integer.parseInt(tailHalfSb.toString());
        String ans1;
        String ans2;
        String ans3;
        // 本身就是回文整数的，将前半截数字减一，或将前半截数字加一，哪个差绝对值最小用哪个方案。
        if (preHalf == tailHalf) {
            int ps1 = preHalf - 1;
            if (String.valueOf(ps1).length() == String.valueOf(preHalf).length()) {
                ans1 = preHalfSubtractOne(ps1, length);
            } else {
                int temp = Integer.parseInt(n.substring(0, length / 2 + length % 2 + 1));
                ans1 = preHalfSubtractOne(temp - 1, length - 1);
            }
            int pa1 = preHalf + 1;
            if (String.valueOf(pa1).length() == String.valueOf(preHalf).length()) {
                ans2 = preHalfSubtractOne(pa1, length);
            } else {
                ans2 = preHalfSubtractOne(pa1, length + 1);
            }
            long abs1 = Math.abs(Long.parseLong(ans1) - Long.parseLong(n));
            long abs2 = Math.abs(Long.parseLong(ans2) - Long.parseLong(n));
            return absMin(abs1, abs2, ans1, ans2);
        } else {
            // 前半截大于后半截的逆序时，将前半截数字减一，或者反转前半截，哪个差绝对值最小用哪个方案。
            int ps1 = preHalf - 1;
            if (String.valueOf(ps1).length() == String.valueOf(preHalf).length()) {
                ans1 = preHalfSubtractOne(preHalf - 1, length);
            } else {
                int temp = Integer.parseInt(n.substring(0, length / 2 + length % 2 + 1));
                ans1 = preHalfSubtractOne(temp - 1, length - 1);
            }
            // 前半截小于后半截的逆序时，将前半截数字加一，或者反转前半截，哪个差绝对值最小用哪个方案。
            int pa1 = preHalf + 1;
            if (String.valueOf(pa1).length() == String.valueOf(preHalf).length()) {
                ans2 = preHalfSubtractOne(preHalf + 1, length);
            } else {
                ans2 = preHalfSubtractOne(preHalf + 1, length - 1);
            }
            ans3 = preHalf(n, length);
            long abs1 = Math.abs(Long.parseLong(ans1) - Long.parseLong(n));
            long abs2 = Math.abs(Long.parseLong(ans2) - Long.parseLong(n));
            long abs3 = Math.abs(Long.parseLong(ans3) - Long.parseLong(n));
            String ans4 = absMin(abs1, abs2, ans1, ans2);
            long abs4 = Math.abs(Long.parseLong(ans4) - Long.parseLong(n));
            return absMin(abs3, abs4, ans3, ans4);
        }
    }
    private String absMin(long abs1, long abs2, String s1, String s2) {
        if (abs1 < abs2) {
            return s1;
        }
        if (abs2 < abs1) {
            return s2;
        }
        if (Long.parseLong(s1) < Long.parseLong(s2)) {
            return s1;
        } else {
            return s2;
        }
    }

    // 前半截数字减一，反转拼接
    private String preHalfSubtractOne(int preHalf, int length) {
        char[] preChars = String.valueOf(preHalf).toCharArray();
        char[] ans = new char[length];
        int i = 0;
        int j = length - 1;
        while (i <= j) {
            ans[i] = preChars[i];
            ans[j] = preChars[i];
            i++;
            j--;
        }
        return String.valueOf(ans);
    }
    // 反转前半截
    private String preHalf(String n, int length) {
        char[] chars = n.toCharArray();
        for (int i=0; i < length / 2; i++) {
            chars[length - 1 - i] = chars[i];
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        // "123888321"
        String s = new N564_h().nearestPalindromic("100");
        System.out.println(s);
    }

}