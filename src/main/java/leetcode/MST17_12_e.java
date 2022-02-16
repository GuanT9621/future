package leetcode;

/**
 * https://leetcode-cn.com/problems/binode-lcci/
 * 面试题 17.12. BiNode
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。
 * 实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 * 返回转换后的单向链表的头节点。 节点数量不会超过 100000。
 *
 * 思路 DFS 中序遍历
 */
public class MST17_12_e {
    TreeNode ans = new TreeNode();
    TreeNode tail = ans;

    public TreeNode convertBiNode(TreeNode root) {
        dfs(root);
        return ans.right;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        root.left = null;
        tail.right = root;
        tail = tail.right;
        dfs(root.right);
    }

}
