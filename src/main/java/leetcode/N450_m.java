package leetcode;

/**
 * https://leetcode.cn/problems/delete-node-in-a-bst/
 * 删除二叉搜索树中的节点
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
 * 返回二叉搜索树（有可能被更新）的根节点的引用。
 * 一般来说，删除节点可分为两个步骤：首先找到需要删除的节点；如果找到了，删除它。
 *
 * 思路 DFS
 * 删除后，将左节点或右节点上位。然后递归处理。
 */
public class N450_m {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.right == null) {
                return root.left;
            }
            if (root.left == null) {
                return root.right;
            }
            // 取比root大的下一个
            TreeNode temp = root.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            root.right = deleteNode(root.right, temp.val);
            temp.right = root.right;
            temp.left = root.left;
            return temp;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        return root;
    }

}
