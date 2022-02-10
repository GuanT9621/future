package leetcode;

/**
 * https://leetcode-cn.com/problems/decode-xored-array/
 * 解码异或后的数组
 * 未知 整数数组 arr 由 n 个非负整数组成。
 * 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。
 * 例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。
 * 给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。请解码返回原数组 arr 。可以证明答案存在并且是唯一的。
 *
 * 思路
 * 公理 A^B=C 那么 A^C=B
 * 故此 可以通过 arr[0] ^ encoded[1] = arr[1]，依次类推
 */
public class N1720_e {

    public static int[] decode(int[] encoded, int first) {
        int[] arr = new int[encoded.length+1];
        arr[0] = first;
        for (int i=0; i< encoded.length; i++) {
            arr[i+1] = arr[i] ^ encoded[i];
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] encoded = new int[]{1, 2, 3};
        int[] decode = decode(encoded, 1);
        for (int i : decode)
            System.out.print(i + " ");
    }

}
