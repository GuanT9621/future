package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 * 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 *
 * 思路一 中序遍历
 * 思路二 记录子树的结点数：如果你需要频繁地查找第 k 小的值，你将如何优化算法？
 * 思路三 平衡二叉搜索树：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 */
public class N230_m {

    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list.get(k-1);
    }
    private static void dfs(TreeNode root, List<Integer> list) {
        if (root.left != null)
            dfs(root.left, list);
        list.add(root.val);
        if (root.right != null)
            dfs(root.right, list);
    }

    public static void main(String[] args) {
        TreeNode t2 = new TreeNode(2);
        TreeNode t1 = new TreeNode(1, null, t2);
        TreeNode t4 = new TreeNode(4);
        TreeNode t3 = new TreeNode(3, t1, t4);
        int i = kthSmallest(t3, 1);
        System.out.println(i);
    }

}
