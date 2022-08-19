package leetcode;

/**
 *
 */
public class N640_m {

    class Solution {

        public String solveEquation(String equation) {
            String[] split = equation.split("=");
            int[] left = less(split[0]);
            int[] right = less(split[1]);
            int x = left[0] - right[0];
            int n = right[1] - left[1];
            if (x == 0) {
                return n == 0 ? "Infinite solutions" : "No solution";
            } else {
                return "x=" + (n / x);
            }
        }

        private int[] less(String string) {
            string += "+";
            int length = string.length();
            int x = 0;
            int n = 0;
            String tmp = "";
            for (int i = 0; i < length; i++) {
                char c = string.charAt(i);
                if (!"".equals(tmp) && (c == '+' || c == '-') ) {
                    if (tmp.contains("x")) {
                        String x1 = tmp.replace("x", "");
                        if ("".equals(x1) || "+".equals(x1)) {
                            x += 1;
                        } else if ("-".equals(x1)) {
                            x += -1;
                        } else {
                            x += Integer.parseInt(x1);
                        }
                    } else {
                        n += Integer.parseInt(tmp);
                    }
                    tmp = "" + c;
                } else {
                    tmp = tmp + c;
                }
            }
            return new int[] {x, n};
        }

    }

    public static void main(String[] args) {
        String s = new N640_m().new Solution().solveEquation("-x=-1");
        System.out.println(s);
    }
}
