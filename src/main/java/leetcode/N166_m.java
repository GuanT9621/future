package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/fraction-to-recurring-decimal/
 * 分数到小数
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * 如果存在多个答案，只需返回 任意一个 。
 * 对于所有给定的输入，保证 答案字符串的长度小于 10^4 。
 *
 * 思路
 * 0 判断正负
 * 1 求出整数部分
 * 2 求出小数部分
 *      2.1 如果余数变成 0，则结果是有限小数，将小数部分拼接到结果中。
 *      2.2 如果找到循环节，则找到循环节的开始位置和结束位置并加上括号，然后将小数部分拼接到结果中。
 * 注意处理最大负数转正数的溢出的情况
 */
public class N166_m {

    public static String fractionToDecimal(int numerator, int denominator) {
        long numeratorLong = (long) numerator;
        long denominatorLong = (long) denominator;
        StringBuilder sb = new StringBuilder();
        if ((numeratorLong<0 && denominatorLong>0) || (numeratorLong>0 && denominatorLong<0))
            sb.append("-");
        numeratorLong = numeratorLong * (numeratorLong < 0 ? -1 : 1);
        denominatorLong = denominatorLong * (denominatorLong < 0 ? -1 : 1);
        sb.append(numeratorLong/denominatorLong);
        long litter = numeratorLong % denominatorLong;
        if (0 != litter) {
            sb.append(".");
            StringBuilder sb2 = new StringBuilder();
            Map<Long, Integer> map = new HashMap<>();
            int i=0;
            while (0 != litter) {
                map.put(litter, i++);
                litter *= 10;
                sb2.append(litter/denominatorLong);
                litter = litter % denominatorLong;
                if (map.containsKey(litter)) { // 存在循环
                    String s1 = sb2.substring(0, map.get(litter));
                    String s2 = sb2.substring(map.get(litter));
                    return sb.append(s1).append("(").append(s2).append(")").toString();
                }
            }
            sb.append(sb2);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = fractionToDecimal(-1, -2147483648);
        System.out.println(s);
    }

}
