package leetcode;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/count-nodes-with-the-highest-score/
 * 统计最高分的节点数目
 * 给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。
 * 同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 parents[0] == -1 。
 * 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。
 * 求出某个节点分数的方法是，将这个节点和与它相连的边全部删除 ，剩余部分是若干个非空子树，这个节点的分数为所有这些子树大小的乘积 。
 * 请你返回有最高得分节点的数目。
 * 条件 n == parents.length   2 <= n <= 105   parents[0] == -1    对于 i != 0，有 0 <= parents[i] <= n - 1
 *
 * 思考过程
 * 将一个节点的边全部删除，肯能出现的结果，有三种：
 * 1 出现一个树，当该节点为叶子节点时，即 parents 里没有同样的父节点，分数为 n - 1
 * 2 出现两个树，1是当该节点为跟节点，2是当该节点为中间节点，只有左子或右子节点，
 *             分数为 (n - x) * x， 1 <= x <= n-1, Max: x=n/2
 * 3 出现三个树，当该节点为中间节点，且有父，左子，右子节点时。
 *             分数为 x * y * (n - x - y)，  1 <= x <= n-1  1 <= y <= n-1-x,    Max: x=n/3, y=n/3
 * 但是由于我们无法确定： 该树的哪种情况的最大值最大，所以只能暴力处理
 *
 * 方法 DFS 深度优先遍历
 * 遍历每一个节点，计算最大值
 *
 */
public class N2049_m {
    long maxScore = 0;
    int ans = 0;
    int n = 0;
    HashMap<Integer, int[]> parentMap = new HashMap<>();

    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        ans = n - 1;
        for (int i=0; i < n; i++) {
            int p = parents[i];
            if (parentMap.containsKey(p)) {
                parentMap.get(p)[1] = i;
            } else {
                parentMap.put(p, new int[]{i, -1});
            }
        }
        parentMap.remove(-1);
        dfs(0);
        return ans;
    }

    private int dfs(int node) {
        long score = 1;
        int size = n - 1;
        int[] children = parentMap.get(node);
        if (null != children) {
            for (int c : children) {
                if (c != -1) {
                    int t = dfs(c);
                    score *= t;
                    size -= t;
                }
            }
        }
        if (node != 0) {
            score *= size;
        }
        if (score == maxScore) {
            ans++;
        } else if (score > maxScore) {
            maxScore = score;
            ans = 1;
        }
        return n - size;
    }

    public static void main(String[] args) {
        int[] parents = new int[]{-1,2,0};
        int i = new N2049_m().countHighestScoreNodes(parents);
        System.out.println(i);
    }

}
