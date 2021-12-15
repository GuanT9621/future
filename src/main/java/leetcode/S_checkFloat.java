package leetcode;

/**
 * 参考 N8_m 题
 * 判断一个字符串是否为 float
 *
 */
public class S_checkFloat {

    public static boolean check(char[] chars) {
        boolean[][] table = new boolean[4][4];
        table[0] = new boolean[]{true, false, true, false};
        table[1] = new boolean[]{true, false, false, false};
        table[2] = new boolean[]{true, false, false, false};
        table[3] = new boolean[]{false, false, false, false};
        int index = 0;
        for (int i=0; i<chars.length-1; i++) {
            char c1 = chars[i];
            char c2 = chars[i+1];
            int i1 = getIndex(c1);
            if (i1 > index) {
                index = i1;
            }
            int i2 = getIndex(c2);
            boolean check = table[index][i2];
            if (!check) {
                return false;
            }
        }
        return true;
    }

    public static int getIndex(char c) {
        if ('0'<=c && '9'>=c) {
            return 0;
        } else if ('-' == c) {
            return 1;
        } else if ('.' == c) {
            return 2;
        } else {
            return 3;
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String s = "1.";
        System.out.println(check(s.toCharArray()));
        Float aFloat = Float.valueOf(s);
        System.out.println(aFloat);
    }

}
