package leetcode;

/**
 * https://leetcode-cn.com/problems/super-palindromes/
 * 超级回文数
 * 如果一个正整数自身是回文数，而且它也是一个回文数的平方，那么我们称这个数为超级回文数。
 * 现在，给定两个正整数 L 和 R （以字符串形式表示），返回包含在范围 [L, R] 中的超级回文数的数目。
 * 1 <= len(L) <= 18    1 <= len(R) <= 18   int(L) <= int(R)    L 和 R 是表示 [1, 10^18) 范围的整数的字符串。
 *
 * 思路
 * 1 先判断是否为会回文数
 * 2 在判断是否为超级回文数
 *      1 回文数平方的结果，那么超级回文数肯定结尾为0、1、4、5、6、9；
 *          排除0，因为超级回文数不可能0开头；
 *          排除9，因为3在交错位一定是9+9，产生进位导致不满足回文
 *          排除5，因为超级回文数结尾5时，它的开放结尾也为5，起始也为5，那么超级回文数起始比为2，产生矛盾
 *          排除6，理由同5。
 *      2 超级回文数只能为 1xx1、4xx4，那么它的回文数只能为 1xx1、2xx2，x的个数范围[0, oo)
 *      3 同理我们移除最外层的数字，里面的数字也要符合超级回文数，那么它的回文数同推理2，但需要包含0
 */
public class N906_h {

    public static int superpalindromesInRange(String left, String right) {
        int ans = 0;
        long l = Long.parseLong(left);
        long r = Long.parseLong(right);
        long i = 1;
        while (i * i < l) // 到达左边界
            i++;
        while (true) {
            long j = i * i;
            if (l <= j && j < r) {
                long jend = j % 10;
                if (jend == 1 || jend == 4) { // jend is 1 4
                    if (pnum(j)) {
                        System.out.println("i="+i+" j="+j);
                        ans++;
                    }
                }
                i=next(i);
            } else {
                break;
            }
        }
        return ans;
    }
    // 下一个数字，必须是回文，必须只包含012，必须1或2结尾
    private static long next(long i) {
        i++;
        while ((i%10 != 1 || i%10 != 2) && !only012(i) && !pnum(i)) {
            i++;
        }
        return i;
    }
    private static boolean only012(long i) {
        long temp = i;
        while (temp != 0) {
            if (2 < temp % 10)
                return false;
            else
                temp /= 10;
        }
        return true;
    }

    private static boolean pnum(long num) {
        char[] chars = String.valueOf(num).toCharArray();
        int i=0;
        int j= chars.length-1;
        while (i<=j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        String l = "38455498359";
        String r = "999999999999999999";
        int i = superpalindromesInRange(l, r);
        System.out.println(i);
    }

}
