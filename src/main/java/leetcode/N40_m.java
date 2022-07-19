package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://leetcode.cn/problems/combination-sum-ii/
 * 组合总和 II
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。
 *
 * 思路1 与39题一样 回溯DFS 然后排序去重复
 * 会出现超时
 *
 * 思路2 回溯 + 哈希表（处理重复问题）
 *
 */
public class N40_m {

    class Solution {
        Set<List<Integer>> ans = new HashSet<>();

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            dfs(candidates, target, 0, new ArrayList<>());
            for (List<Integer> list : ans) {
                list.sort(Integer::compareTo);
            }
            return new ArrayList<>(ans);
        }

        private void dfs(int[] candidates, int target, int s, List<Integer> list) {
            if (target == 0) {
                // todo
                // copy elements
                ArrayList<Integer> arrayList = new ArrayList<>(list);
                arrayList.sort(Integer::compareTo);
                ans.add(arrayList);
                return;
            }
            for (int i = s; i < candidates.length; i++) {
                int num = candidates[i];
                if (num > target) {
                    continue;
                }
                list.add(num);
                dfs(candidates, target - num, i + 1, list);
                // 回溯
                list.remove(list.size() - 1);
            }
        }
    }

    class Solution2 {
        Set<List<Integer>> ans = new HashSet<>();

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            backtracking(candidates, target, 0, new ArrayList<>());
            return new ArrayList<>(ans);
        }

        private void backtracking(int[] candidates, int target, int index, List<Integer> temp) {
            if (target == 0) {
                ArrayList<Integer> arrayList = new ArrayList<>(temp);
                arrayList.sort(Integer::compareTo);
                ans.add(arrayList);
                return;
            }
            if (target < 0 || index >= candidates.length) {
                return;
            }
            ArrayList<Integer> list = new ArrayList<>(temp);
            backtracking(candidates, target, index + 1, list);
            list.add(candidates[index]);
            backtracking(candidates, target - candidates[index], index + 1, list);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new N40_m().new Solution2().combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
        for (List<Integer> list : lists) {
            System.out.println(list.stream().map(Objects::toString).collect(Collectors.joining(",")));
        }
    }

}
