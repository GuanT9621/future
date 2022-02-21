package leetcode;

/**
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 * 二叉树中的最大路径和
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 思路
 * 像这种最大和的题目，每个节点都需要决定下一个节点去哪儿，这属于典型的动态规划，类似于背包问题，黄金矿工，等决策问题。
 * 而动态规划的解法有：大暴搜，记忆化搜索，动态规划转移方程处理（递归，迭代）
 * 转移方程 ： Sum(node) = Max(Sum(left), Sum(right), Sum(left)+Sum(right)+Node)
 */
public class N123_h {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxSum(root);
        return max;
    }

    private int maxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = Math.max(maxSum(root.left), 0);
        int r = Math.max(maxSum(root.right), 0);
        int curr = l + root.val + r;
        // 更新答案
        max = Math.max(max, curr);
        // 返回节点的最大贡献值
        return root.val + Math.max(l, r);
    }

    public int maxPathSum2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = maxPathSum2(root.left);
        int r = maxPathSum2(root.right);
        int curr = l + root.val + r;
        int cl = l + root.val;
        int cr = r + root.val;
        return Math.max(l, Math.max(r, Math.max(curr, Math.max(cl, cr))));
    }

}
