package leetcode;

/**
 * https://leetcode.cn/problems/find-bottom-left-tree-value/
 * 找树左下角的值
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 * 假设二叉树中至少有一个节点。
 *
 * 思路 dfs
 * 遍历二叉树，记录当前的层级，取到最高层级。
 * 先序遍历，中-左-右，保证取到最左。
 */
public class N513_m {

    int level;
    int ans;

    public int findBottomLeftValue(TreeNode root) {
        level = 0;
        ans = root.val;
        dfs(root, level);
        return ans;
    }

    private void dfs(TreeNode node, int currLevel) {
        if (currLevel > level) {
            level = currLevel;
            ans = node.val;
        }
        if (node.left != null) {
            dfs(node.left, currLevel + 1);
        }
        if (node.right != null) {
            dfs(node.right, currLevel + 1);
        }
    }

}
