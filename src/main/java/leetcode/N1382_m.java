package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/balance-a-binary-search-tree/
 * 将二叉搜索树变平衡
 * 给你一棵二叉搜索树，请你返回一棵平衡后的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。
 * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是平衡的 。
 * 思路：
 * 1 先通过中序遍历，保存树的数据到列表里
 * 2 然后构建新的平衡二叉树
 */
public class N1382_m {
    List<Integer> inorderList = new ArrayList<Integer>();

    public TreeNode balanceBST(TreeNode root) {
        getInorder(root);
        return build(0, inorderList.size() - 1);
    }

    public void getInorder(TreeNode node) {
        if (null != node.left)
            getInorder(node.left);
        inorderList.add(node.val);
        if (null != node.right)
            getInorder(node.right);
    }
    // 构建一个平衡二叉树
    public TreeNode build(int l, int r) {
        int mid = (l + r) >> 1; // 右移一位等于除以2
        TreeNode node = new TreeNode(inorderList.get(mid));
        if (l <= mid - 1) {
            node.left = build(l, mid - 1);
        }
        if (mid + 1 <= r) {
            node.right = build(mid + 1, r);
        }
        return node;
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;this.left = left;this.right = right;
      }
    }
}
