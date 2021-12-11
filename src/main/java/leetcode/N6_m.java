package leetcode;

/**
 * https://leetcode-cn.com/problems/zigzag-conversion/
 * Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 思路
 * 有一个浮标在 1 - numRows 之间浮动，当触及到 1 或者 numRow 时，则开始反转
 * 浮标指向的是每一行。
 *
 */
public class N6_m {

    public static String convert(String s, int numRows) {
        if (numRows < 2) return s;
        StringBuilder[] ss = new StringBuilder[numRows];
        for (int i=0; i<numRows; i++) ss[i]=new StringBuilder();
        int i = 0;
        int flag = -1;
        for(char c : s.toCharArray()) {
            ss[i].append(c);
            if(i == 0 || i == numRows -1) flag = - flag;
            i = i + flag;
        }
        StringBuilder rs = new StringBuilder();
        for (StringBuilder sb : ss) rs.append(sb);
        return rs.toString();
    }


    public static void main(String[] args) {
        String s = convert("PAYPALISHIRING", 3);
        System.out.println(s);
    }
}
