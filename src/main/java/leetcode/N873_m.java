package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence/
 * 最长的斐波那契子序列的长度
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 * n >= 3 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 *
 * 思路 遍历 + 哈希表
 *
 * 思路
 * 注意给定的条件'严格递增'
 *
 */
public class N873_m {

    class Solution {

        public int lenLongestFibSubseq(int[] arr) {
            Set<Integer> set = new HashSet<>();
            for (int i : arr) {
                set.add(i);
            }
            int n = arr.length;
            int ans = 0;
            for (int i = 0; i < n - 2; i++) {
                for (int j = i + 1; j < n - 1; j++) {
                    int a1 = arr[i];
                    int a2 = arr[j];
                    int a3 = a1 + a2;
                    int count = 0;
                    while (set.contains(a3)) {
                        count++;
                        a1 = a2;
                        a2 = a3;
                        a3 = a1 + a2;
                    }
                    ans = Math.max(ans, count);
                }
            }
            return ans == 0 ? 0 : (ans + 2);
        }

    }

}
