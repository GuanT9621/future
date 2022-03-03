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
 * 建立 numRows 个数组存储 Z 形每一行的数据，然后合并这些行即可。
 * 所以问题在于怎么将原有字符串，分别存储到 numRows 对应的每个数组里。
 * 浮标代表是所在一行：浮标在 1 - numRows 之间浮动，当触及到 1 或者 numRow 时，则开始反转，
 * 使用 flag = -1，来处理每次位移的方向。
 */
public class N6_m {

    public static String convert(String s, int numRows) {
        if (numRows < 2)
            return s;
        // 反转后的每一行
        StringBuilder[] ss = new StringBuilder[numRows];
        for (int i=0; i<numRows; i++)
            ss[i] = new StringBuilder();
        int i = 0;
        // 浮标下次移位的方向
        int flag = -1;
        for(char c : s.toCharArray()) {
            ss[i].append(c);
            if(i == 0 || i == numRows -1)
                flag = - flag;
            i = i + flag;
        }
        // 合并每行结果
        StringBuilder rs = new StringBuilder();
        for (StringBuilder sb : ss)
            rs.append(sb);
        return rs.toString();
    }

    public static void main(String[] args) {
        String s = convert("PAYPALISHIRING", 3);
        System.out.println(s);
    }
}
