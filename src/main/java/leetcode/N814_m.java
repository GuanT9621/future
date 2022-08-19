package leetcode;

/**
 * 后续遍历 dfs 递归
 */
public class N814_m {

    class Solution {

        public TreeNode pruneTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            root.left = pruneTree(root.left);
            root.right = pruneTree(root.right);
            if (root.left == null && root.right == null && root.val == 0) {
                return null;
            }
            return root;
        }

    }

}
