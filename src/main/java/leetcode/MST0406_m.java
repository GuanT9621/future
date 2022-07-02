package leetcode;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/successor-lcci/
 * 后继者
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 *
 * 思路一 中序遍历
 * 思路二 利用二叉搜索树，搜索遍历，因为二叉树的中序遍历是有序的
 */
public class MST0406_m {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        boolean find = false;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                if (find) {
                    return treeNode;
                }
                if (p.val == treeNode.val) {
                    find = true;
                }
                treeNode = treeNode.right;
            }
        }
        return null;
    }


    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        if (p.right != null) {
            successor = p.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        }
        TreeNode node = root;
        while (node != null) {
            if (node.val > p.val) {
                successor = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return successor;
    }

}
