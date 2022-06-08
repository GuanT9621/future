package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/minimum-genetic-mutation/
 * 最小基因变化
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 *
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
 *
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
 * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
 * 0 <= bank.length <= 10
 *
 * 思路 搜索 DFS or BFS
 * 因为每一次只变化一个位置，且下一次变化依赖上一次的信息，故可以认为是一个树形结构
 * 所以设 start 为 root，设 end 为叶子节点，判断是否存在从 root 到 end 的路径，并返回最短路径的长度
 * 注意：有可能存在多个路径，需要对比获得最短路径，返回上一层记得将 count - 1
 *
 */
public class N433_m {
    int ans = Integer.MAX_VALUE;

    public int minMutation(String start, String end, String[] bank) {
        dfs(0, start, end, Arrays.asList(bank));
        return ans > 8 ? -1 : ans;
    }

    private void dfs(Integer count, String root, String target, List<String> bank) {
        for (String b : bank) {
            if (onceDiff(root, b)) {
                count++;
                if (b.equals(target)) {
                    ans = Math.min(ans, count);
                } else {
                    List<String> newBank = new ArrayList<>(bank);
                    newBank.remove(b);
                    dfs(count, b, target, newBank);
                }
                count--;
            }
        }
    }

    private boolean onceDiff(String a, String b) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            count += a.charAt(i) == b.charAt(i) ? 0 : 1;
        }
        return count == 1;
    }

    public static void main(String[] args) {
        String start = "AAAACCCC";
        String end = "CCCCCCCC";
        String[] bank = new String[]{"AAAACCCA","AAACCCCA","AACCCCCA","AACCCCCC","ACCCCCCC","CCCCCCCC","AAACCCCC","AACCCCCC"};
        int i = new N433_m().minMutation(start, end, bank);
        System.out.println(i);
    }

}
