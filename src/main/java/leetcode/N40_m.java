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
 * 思路2
 *
 */
public class N40_m {
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

    public static void main(String[] args) {
        List<List<Integer>> lists = new N40_m().combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
        for (List<Integer> list : lists) {
            System.out.println(list.stream().map(Objects::toString).collect(Collectors.joining(",")));
        }
    }

}
