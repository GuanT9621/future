package leetcode;

/**
 * https://leetcode-cn.com/problems/decode-xored-permutation/
 * 解码异或后的排列
 * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。
 * 比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 *
 * 思路
 * 一个数与另一个数异或两次是其本身； 一个数与自身异或，结果为零； 一个数与零异或，结果为其本身。
 * 公理 A^B=C 那么 A^C=B
 * perm=[A, B, C, D, E]     encoded=[AB, BC, CD, DE]
 * 那么 ABCDE = 1^2^...^5， 而 BCDE= BC^DE，则 A=ABCDE^BCDE
 *
 */
public class N1734_m {

    public static int[] decode(int[] encoded) {
        int length = encoded.length;
        int permResult = 0;
        for (int i=1; i<=encoded.length+1; i++) {
            permResult ^= i;
        }
        int encodedResult = 0;
        for (int i=1; i<encoded.length; i=i+2) {
            encodedResult ^= encoded[i];
        }
        int[] perm = new int[length+1];
        perm[0] = permResult ^ encodedResult;
        for (int i=0; i<encoded.length; i++) {
            perm[i+1] = perm[i] ^ encoded[i];
        }
        return perm;
    }

    public static void main(String[] args) {
        int[] encoded = new int[]{6,5,4,6};
        int[] decode = decode(encoded);
        for (int i : decode)
            System.out.print(i + " ");
    }
}
