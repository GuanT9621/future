package leetcode;

/**
 * https://leetcode-cn.com/problems/strong-password-checker/
 * 强密码检验器
 * 如果一个密码满足下述所有条件，则认为这个密码是强密码：
 * 1 由至少 6 个，至多 20 个字符组成。
 * 2 至少包含 一个小写 字母，一个大写 字母，和 一个数字 。
 * 3 同一字符 不能 连续出现三次 (比如 "...aaa..." 是不允许的, 但是 "...aa...a..." 如果满足其他条件也可以算是强密码)。
 * 给你一个字符串 password ，返回 将 password 修改到满足强密码条件需要的最少修改步数。如果 password 已经是强密码，则返回 0 。
 *
 * 在一步修改操作中，你可以：
 * 插入一个字符到 password ，从 password 中删除一个字符，或 用另一个字符来替换 password 中的某个字符。
 *
 * 1 <= password.length <= 50   password 由字母、数字、点 '.' 或者感叹号 '!'
 *
 * 思路 分类模拟
 * 小于6 只有add upd， 等于6 只有 upd ，大于6 只有 del upd
 * 1 遍历记录是否满足t2，记录步数
 * 2 遍历时判断三次以上，记录步数(滑动窗口)
 * 3 判断长度是否满足t1，记录步数
 */
public class N420_h {

    public int strongPasswordChecker(String password) {
        int n = password.length();
        if (n < 6) {
            return little(password);
        }
        if (n > 20) {
            return big(password);
        }
        return match(password);
    }

    // only upd or add
    private int little(String password) {
        int n = password.length();
        int mustAdd = 6 - n;
        int kinds = 0;
        int prev = 0;
        int upd = 0;
        boolean ca = false;
        boolean cA = false;
        boolean c1 = false;
        for (int i = 0; i < n; i++) {
            char c = password.charAt(i);
            ca = ca || ('a' <= c && c <= 'z');
            cA = cA || ('A' <= c && c <= 'Z');
            c1 = c1 || ('0' <= c && c <= '9');
            if (password.charAt(prev) == c) {
                if (i - prev + 1 > 2) {
                    upd++;
                    prev = i + 1;
                }
            } else {
                prev = i;
            }
        }
        kinds += ca ? 0 : 1;
        kinds += cA ? 0 : 1;
        kinds += c1 ? 0 : 1;
        return Math.max(mustAdd, Math.max(kinds, upd));
    }
    // only upd or del
    private int big(String password) {
        int n = password.length();
        int mustDel = n - 20;
        int kinds = 0;
        int prev = 0;
        int upd = 0;
        boolean ca = false;
        boolean cA = false;
        boolean c1 = false;
        for (int i = 0; i < n; i++) {
            char c = password.charAt(i);
            ca = ca || ('a' <= c && c <= 'z');
            cA = cA || ('A' <= c && c <= 'Z');
            c1 = c1 || ('0' <= c && c <= '9');
            if (password.charAt(prev) == c) {
                if (i - prev + 1 > 2) {
                    upd++;
                    prev = i + 1;
                }
            } else {
                prev = i;
            }
        }
        kinds += ca ? 0 : 1;
        kinds += cA ? 0 : 1;
        kinds += c1 ? 0 : 1;
        return mustDel + Math.max(kinds, upd % 3);
    }
    // only upd
    private int match(String password) {
        int n = password.length();
        int kinds = 0;
        int prev = 0;
        int upd = 0;
        boolean ca = false;
        boolean cA = false;
        boolean c1 = false;
        for (int i = 0; i < n; i++) {
            char c = password.charAt(i);
            ca = ca || ('a' <= c && c <= 'z');
            cA = cA || ('A' <= c && c <= 'Z');
            c1 = c1 || ('0' <= c && c <= '9');
            if (password.charAt(prev) == c) {
                if (i - prev + 1 > 2) {
                    upd++;
                    prev = i + 1;
                }
            } else {
                prev = i;
            }
        }
        kinds += ca ? 0 : 1;
        kinds += cA ? 0 : 1;
        kinds += c1 ? 0 : 1;
        return Math.max(kinds, upd);
    }

    public static void main(String[] args) {
        int s = new N420_h().strongPasswordChecker("bbaaaaaaaaaaaaaaacccccc");
        System.out.println(s);
    }

}
