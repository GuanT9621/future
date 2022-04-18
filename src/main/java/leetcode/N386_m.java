package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/lexicographical-numbers/
 * 字典序排数
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 *
 * 思路一 多叉树的先序遍历 DFS
 *  0
 *  1 2 3 4 ...
 *  10 11 12... 21 22 23... 31 32 33... 41 42 43... ...
 *  100 101 102... 110 111 112... ...
 *
 */
public class N386_m {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int number = 1;
        for (int i=0; i < n ; i++) {
            ans.add(number);
            if (number * 10 <= n) {
                number *= 10;
            } else {
                // 回上一层
                while (number % 10 == 9 || number + 1 > n) {
                    number /= 10;
                }
                // 在上一层加一
                number++;
            }
        }
        return ans;
    }

}
