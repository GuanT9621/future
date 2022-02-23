package leetcode;

/**
 * https://leetcode-cn.com/problems/1-bit-and-2-bit-characters/
 * 1比特与2比特字符
 * 有两种特殊字符：
 * 第一种字符可以用一个比特 0 来表示
 * 第二种字符可以用两个比特(10 或 11)来表示、
 * 给定一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一位字符，则返回 true 。
 * 条件 1 <= bits.length <= 1000      bits[i] == 0 or 1
 *
 * 思路 正序遍历
 * 1 以0结尾，那么上一位为0，则必然最后一个字符是一位字符，返回true；
 * 2 从左到右遍历到 bits[i] 时，如果 bits[i]=0，说明遇到了第一种字符，将 i 的值增加 1；
 *  如果bits[i]=1，说明遇到了第二种字符，可以跳过 bits[i+1]， 将 i 的值增加 2。
 *
 * 思路 逆序遍历
 * 1 以0结尾，那么上一位为0，则必然最后一个字符是一位字符，返回true；
 * 2 寻找倒数第二个0的位置，找不到则为-1，从该位置向下全是1，判断这些1的奇偶，奇数则为false，偶数为true
 */
public class N717_e {

    public boolean isOneBitCharacter(int[] bits) {
        int length = bits.length;
        if (length == 1)
            return true;
        if (bits[length -2] == 0)
            return true;
        int i=0;
        while (i < length-1) {
            i += bits[i] == 0 ? 1 : 2;
        }
        return i == length-1;
    }

    public boolean isOneBitCharacter2(int[] bits) {
        int length = bits.length;
        if (length == 1)
            return true;
        int i=-1;
        for (int j=length-2; j>=0; j--) {
            if (bits[j] == 0) {
                i = j;
                break;
            }
        }
        return (length - i) % 2 == 0;
    }

}
