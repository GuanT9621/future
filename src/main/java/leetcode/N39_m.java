package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * https://leetcode.cn/problems/combination-sum/
 * 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
 * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 * 思路 回溯 递归 DFS
 */
public class N39_m {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0, new ArrayList<>());
        return ans;
    }

    private void dfs(int[] candidates, int target, int s, List<Integer> list) {
        if (target == 0) {
            // copy elements
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = s; i < candidates.length; i++) {
            int num = candidates[i];
            if (num > target) {
                continue;
            }
            list.add(num);
            dfs(candidates,  target - num, i, list);
            // 回溯
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new N39_m().combinationSum(new int[]{2, 3, 5}, 8);
        for (List<Integer> list : lists) {
            System.out.println(list.stream().map(Objects::toString).collect(Collectors.joining(",")));
        }
    }

}
