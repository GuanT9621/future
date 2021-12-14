package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations/
 * 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 思路
 * 按照排列组合原理 排列为 A(n, m) = n!/(n-m)! 组合为 C(n,m) = n!/m!(n-m)!
 * 解决这个问题就是一种遍历的方式，可以采用深度优先或者广度优先。
 *
 * 方法一 标记数组
 * 方法二 回溯算法（DFS），如果题目要求按字典序输出，那么还是用标记数组或者其他方法。
 *
 * 回溯法：一种通过探索所有可能的候选解来找出所有的解的算法。
 * 如果候选解被确认不是一个解（或者至少不是最后一个解），回溯算法会通过在上一步进行一些变化抛弃该解，即回溯并且再次尝试。
 *
 */
public class N46_m {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }
        int n = nums.length;
        dfs(n, output, result, 0);
        return result;
    }

    public static void dfs(int n, List<Integer> output, List<List<Integer>> result, int first) {
        // 所有数都填完了
        if (first == n) {
            result.add(new ArrayList<>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            dfs(n, output, result, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> permute = permute(nums);
        for (List<Integer> list : permute) {
            for (Integer i : list) {
                System.out.print(i+",");
            }
            System.out.println();
        }
    }
}
