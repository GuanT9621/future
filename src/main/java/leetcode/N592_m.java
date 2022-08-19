package leetcode;

/**
 * 遍历
 */
public class N592_m {

    class Solution {

        public String fractionAddition(String expression) {
            String a = "";
            String b = "";
            for (char c : expression.toCharArray()) {
                if (c == '-' || c == '+') {
                    if (a.length() == 0) {
                        a = b;
                    } else {
                        a = add(a, b);
                    }
                    b = "" + c;
                } else {
                    b += c;
                }
            }
            if ("".equals(a)) {
                return b;
            }
            return add(a, b);
        }

        private String add(String a, String b) {
            int fa = 1;
            int fb = 1;
            if (a.indexOf(0) == '-') {
                fa = -1;
                a = a.substring(1);
            }
            if (b.indexOf(0) == '-') {
                fb = -1;
                b = b.substring(1);
            }
            int a1 = Integer.parseInt(a.split("/")[0]);
            int a2 = Integer.parseInt(a.split("/")[1]);
            int b1 = Integer.parseInt(b.split("/")[0]);
            int b2 = Integer.parseInt(b.split("/")[1]);
            int c1 = fa * a1 * b2 + fb * b1 * a2;
            int c2 = a2 * b2;
            int fc = 1;
            if (c1 < 0) {
                fc = -1;
                c1 *= -1;
            }
            int gcd = gcd(Math.max(c1, c2), Math.min(c1, c2));
            if (gcd > 1) {
                c1 /= gcd;
                c2 /= gcd;
            }
            return fc == 1 ? String.format("%s/%s", c1, c2) : String.format("-%s/%s", c1, c2);
        }

        private int gcd(int m, int n) {
            return n == 0 ? m : gcd(n, m % n);
        }
    }

    public static void main(String[] args) {
        String x = "1/3-1/2";
        String s = new N592_m().new Solution().fractionAddition(x);
        System.out.println(s);
    }

}
