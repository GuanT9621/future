package algorithm.normal;

/**
 * 判断一个数是不是2的次方
 * 使用二进制方式：
 *   · 一个数 n 是2的次方，那么二进制为 10000...
 *   · n-1 是 01111...
 *   · 所以 n&(n-1) 为 0
 */
public class PowerOf2 {

    static boolean isPower(int n) {
        return (n & (n-1)) == 0;
    }

    public static void main(String[] args) {
        System.out.println(isPower(12));
        System.out.println(isPower(16));
    }
}
