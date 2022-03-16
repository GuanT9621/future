package leetcode;

/**
 * https://leetcode-cn.com/problems/utf-8-validation/
 * UTF-8 编码验证
 * 给定一个表示数据的整数数组 data ，返回它是否为有效的 UTF-8 编码。
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 *      对于 1 字节的字符，字节的第一位设为 0 ，后面 7 位为这个符号的 unicode 码。
 *      对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为 0 ，后面字节的前两位一律设为 10 。
 *              剩下的没有提及的二进制位，全部为这个符号的 unicode 码。
 * 这是 UTF-8 编码的工作方式：
 *
 *    Char. number range  |        UTF-8 octet sequence
 *       (hexadecimal)    |              (binary)
 *    --------------------+---------------------------------------------
 *    0000 0000-0000 007F | 0xxxxxxx
 *    0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 *    0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 *    0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 注意：输入是整数数组。只有每个整数的 最低 8 个有效位 用来存储数据。这意味着每个整数只表示 1 字节的数据。
 * 提示:  1 <= data.length <= 2 * 10^4     0 <= data[i] <= 255
 *
 * 例如
 * 输入：data = [197,130,1]
 * 输出：true
 * 解释：数据表示字节序列: 11000101 10000010 00000001
 *
 * 思路 模拟-遍历
 * 区分当前的 num 所在范围 [0, 2^7-1] [2^7+2^6, 2^7+2^6+2^5-1], [2^7+2^6+2^5, 2^7+2^6+2^5+2^4-1], [2^7+2^6+2^5+2^4, 2^7+2^6+2^5+2^4+2^3-1]
 * 即 [0, 127] [192, 223] [224, 239] [240, 247]
 * 后续范围为 [128, 191]
 */
public class N393_m {

    public boolean validUtf8(int[] data) {
        int n = data.length;
        for (int i=0; i < n; i++) {
            int num = data[i];
            if (num >= 0 && num <= 127) {

            } else if (num >= 192 && num <= 223) {
                i++;
                if (i >= n || data[i] < 128 || data[i] > 191) {
                    return false;
                }
            } else if (num >= 224 && num <= 239) {
                i++;
                if (i >= n || data[i] < 128 || data[i] > 191) {
                    return false;
                }
                i++;
                if (i >= n || data[i] < 128 || data[i] > 191) {
                    return false;
                }
            } else if (num >= 240 && num <= 247) {
                i++;
                if (i >= n || data[i] < 128 || data[i] > 191) {
                    return false;
                }
                i++;
                if (i >= n || data[i] < 128 || data[i] > 191) {
                    return false;
                }
                i++;
                if (i >= n || data[i] < 128 || data[i] > 191) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int [] data = new int[]{197,130,1};
        new N393_m().validUtf8(data);
    }

}
