package leetcode;

/**
 * https://leetcode-cn.com/problems/complex-number-multiplication/
 * 复数乘法
 * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
 * 实部 是一个整数，取值范围是 [-100, 100]
 * 虚部 也是一个整数，取值范围是 [-100, 100]
 * i2 == -1
 * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串
 *
 * 思路 模拟
 * 实现 (a+bi)(c+di) = (ac-bd)+(bc+ad)i
 */
public class N537_m {

    public String complexNumberMultiply(String num1, String num2) {
        String[] s1 = num1.split("\\+|i", -1);
        String[] s2 = num2.split("\\+|i", -1);
        int a = Integer.parseInt(s1[0]);
        int b = Integer.parseInt(s1[1]);
        int c = Integer.parseInt(s2[0]);
        int d = Integer.parseInt(s2[1]);
        return (a * c - b * d) + "+" + (b * c + a * d) + "i";
    }

    public static void main(String[] args) {
//        String num1 = "1+1i";
//        String num2 = "1+1i";
        String num1 = "1+-1i";
        String num2 = "1+-1i";
        String s = new N537_m().complexNumberMultiply(num1, num2);
        System.out.print(s);
    }

}
