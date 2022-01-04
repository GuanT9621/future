package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 将字符串转为数字
 * 将数字转为字符串
 *
 */
public class S_s2i_i2s {
    private Integer result = 0;
    private String status = "start";
    private int sign = 1;
    private Map<String, String[]> table  = new HashMap<>();

    public S_s2i_i2s() {
        // start int +- other
        table.put("start", new String[]{"start", "int", "sign", "end"});
        table.put("int",   new String[]{"end", "int", "end", "end"});
        table.put("sign",  new String[]{"end", "int", "end", "end"});
        table.put("end",   new String[]{"end", "end", "end", "end"});
    }

    public int stringToInt(String str) {
        for (char c : str.toCharArray()) {
            int index = getIndex(c);
            this.status = table.get(status)[index];
            if ("sign".equals(this.status)) {
                sign = '+' == c ? 1 : -1;
            } else if ("end".equals(this.status)) {
                throw new RuntimeException();
            } else {
                int temp = result * 10;
                // 判断溢出
                if (Integer.MAX_VALUE / 10 < result) {
                    throw new RuntimeException("溢出了");
                }
                int ci = c - '0';
                result = temp + ci;
                // 判断溢出
                if (Integer.MAX_VALUE - ci < temp) {
                    throw new RuntimeException("溢出了");
                }
            }
        }
        return sign * result;
    }

    private int getIndex(char c) {
        if (' ' == c) {
            return 0;
        }
        if ('0' <= c && '9' >= c) {
            return 1;
        }
        if ('-' == c || '+' == c) {
            return 2;
        }
        return 3;
    }

    public static String intToStr(Integer i) {
        if (null == i) {
            return null;
        }
        if (0 == i) {
            return "0";
        }
        StringBuilder s = new StringBuilder(i < 0 ? "-" : "");
        int tmp = i < 0 ? -1 * i : i;
        Stack<Integer> stack = new Stack<>();
        while (tmp != 0) {
            stack.push(tmp % 10);
            tmp = tmp / 10;
        }
        while (!stack.isEmpty()) {
            s.append(stack.pop());
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Integer i = -12300;
        String s = intToStr(i);
        System.out.println(s);

        S_s2i_i2s test = new S_s2i_i2s();
        int i1 = test.stringToInt(s);
        System.out.println(i1);
    }

}
