package leetcode;

/**
 * https://leetcode-cn.com/problems/integer-to-roman/
 * 整数转罗马数字
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
 * 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 *
 * 给你一个整数，将其转为罗马数字。
 *
 * 思路一 模拟   根据罗马数字的唯一表示法，为了表示一个给定的整数 num，我们寻找不超过 num 的最大符号值，将 num 减去该符号值，
 *              然后继续寻找不超过 num 的最大符号值，将该符号拼接在上一个找到的符号之后，循环直至 num 为 0。
 *              最后得到的字符串即为 num 的罗马数字表示。
 *
 * 思路二 硬编码 回顾前言中列出的这 1313 个符号，可以发现：
 *             千位数字只能由 M 表示；
 *             百位数字只能由 C，CD，D 和 CM 表示；
 *             十位数字只能由 X，XL，L 和 XC 表示；
 *             个位数字只能由 I，IV，V 和 IX 表示。
 *             所以，你可以画出每个位上的全部取值：
 *             千位 百位  十位  个位
 *           0  -   -    -    -
 *           1  M   C    X    I
 *           2  MM  CC   XX   II
 *           3  MMM CCC  XXX  III
 *           4  -   CD   XL   IV
 *           5  -   D    L    V
 *           6  -   DC   LX   VI
 *           7  -   DCC  LXX  VII
 *           8  -   DCCC LXX  VIII
 *           9  -   CM   XC   IX
 */
public class N12_m {

    // 1 <= num <= 3999
    public static String intToRoman(int num) {
        int[] values = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num = num - value;
                roman.append(symbol);
            }
            if (num == 0)
                break;
        }
        return roman.toString();
    }

    public static String intToRoman2(int num) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds  = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens      = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        StringBuilder roman = new StringBuilder();
        roman.append(thousands[num / 1000]);
        roman.append(hundreds[num % 1000 / 100]);
        roman.append(tens[num % 100 / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }
    public static void main(String[] args) {
        int num = 1994;
        String s = intToRoman(num);
        System.out.println(s);
    }

}
