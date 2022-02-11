package leetcode;

/**
 * https://leetcode-cn.com/problems/integer-to-english-words/
 * 整数转换英文表：将非负整数 num 转换为其对应的英文表示。0 <= num <= 2^31 - 1， 0 - 4,294,967,295
 *
 * 示例 输入：num = 123  输出："One Hundred Twenty Three"
 * 输入：num = 12,345    输出："Twelve Thousand Three Hundred Forty Five"
 * 输入：num = 1,234,567  输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * 1234567891 "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *
 * 实现方式 递归 or 迭代
 * 可以参考官方代码更简洁。
 *
 * 思路 规则匹配
 * 1 百万 千 百 Million Thousand Hundred
 * 2 twenty thirty forty fifty sixty seventy eighty ninety
 * 3 十 ten eleven twelve thirteen fourteen fifteen sixteen seventeen eighteen nineteen
 * 4 个 zero one two three four five six seven eight nine
 */
public class N273_h {
    static String[] m1 = new String[]{" Billion"," Million"," Thousand"," Hundred"};
    static String[] m2 = new String[]{"", "", " Twenty"," Thirty"," Forty"," Fifty"," Sixty"," Seventy"," Eighty"," Ninety"};
    static String[] m3 = new String[]{" Ten"," Eleven"," Twelve"," Thirteen"," Fourteen"," Fifteen"," Sixteen"," Seventeen"," Eighteen", " Nineteen"};
    static String[] m4 = new String[]{""," One"," Two"," Three"," Four"," Five"," Six"," Seven"," Eight"," Nine"};

    public static String numberToWords(int num) {
        return untrimNumberToWords(num).trim();
    }

    private static String untrimNumberToWords(int num) {
        if (num == 0)
            return "Zero";
        int i0 = num / 1000000000;
        int i1 = num % 1000000000 / 1000000;
        int i2 = num % 1000000 / 1000;
        int i3 = num % 1000;
        StringBuilder result = new StringBuilder();
        if (i0 != 0) {
            result.append(untrimNumberToWords(i0)).append(m1[0]);
        }
        if (i1 != 0) {
            result.append(untrimNumberToWords(i1)).append(m1[1]);
        }
        if (i2 != 0) {
            result.append(untrimNumberToWords(i2)).append(m1[2]);
        }
        if (i3 != 0) {
            int a = i3 / 100;
            if (a > 0) {
                result.append(m4[a]).append(m1[3]);
            }
            int b = i3 % 100 / 10;
            if (b > 1) {
                result.append(m2[b]);
            }
            int c = i3 % 100 % 10;
            if (b == 1) {
                result.append(m3[c]);
            } else {
                result.append(m4[c]);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
//        int num = 1234567;
        int num = 1234567891;
        String s = numberToWords(num);
        System.out.println("Ans=\"" + s + "\"");
    }
}
