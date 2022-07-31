package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/next-greater-element-iii/
 * 下一个更大元素 III
 * 给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。
 * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
 * 1 <= n <= 2^31 - 1
 *
 * 思路
 * 找到第一个逆序的数字 如 413210 中的 1 3，
 * 然后将较小的那个数字 1 替换为 后半 3210 中比当前大的数 2 ，即 423110
 * 然后将后半中 3110 逆向，即 420113 即可。
 *
 * 注意如果上面的条件中数字 1 的位置在首位，即 13210 时
 * 那么后半需要排除 0 的最小，即 13210
 * 然后逆向后半部 20113
 *
 * 12222333
 *
 * 12223332
 * 12223233
 */
public class N556_m {

    static class Solution {
        public int nextGreaterElement(int n) {
            int[] array = toArray(n);
            int target = -1;
            for (int i = 0; i < array.length; i++) {
                if (i + 1 < array.length) {
                    if (array[i] < array[i + 1]) {
                        target = i;
                    }
                }
            }
            if (target == -1) {
                return -1;
            }
            int replace = target + 1;
            for (int i = target + 1; i < array.length; i++) {
                if (array[i] > array[target] && array[i] < array[replace]) {
                    replace = i;
                }
            }
            int temp = array[target];
            array[target] = array[replace];
            array[replace] = temp;
            String ans = "";
            for (int i = 0; i <= target; i++) {
                ans += array[i];
            }
            List<Integer> sub = new ArrayList<>();
            for (int i = array.length - 1; i > target; i--) {
                sub.add(array[i]);
            }
            int[] subArray = listToArray(sub);
            Arrays.sort(subArray);
            for (int i = 0; i < subArray.length; i++) {
                ans += subArray[i];
            }
            long l = Long.parseLong(ans);
            return l > Integer.MAX_VALUE ? -1 : (int) l;
        }
        private int[] toArray(int n) {
            List<Integer> list = new ArrayList<>();
            while (n != 0) {
                int i = n % 10;
                n /= 10;
                list.add(i);
            }
            return listToArray(list);
        }

        private int[] listToArray(List<Integer> list) {
            int[] array = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(list.size() - 1 - i);
            }
            return array;
        }


        public static void main(String[] args) {
            int i = new Solution().nextGreaterElement(1999999999);
            System.out.println(i);
        }
    }

}
