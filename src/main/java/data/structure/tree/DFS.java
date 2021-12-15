package data.structure.tree;

public class DFS {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void print(TreeNode treeNode) {
        if (null != treeNode) {
            System.out.print(treeNode.val);
            print(treeNode.left);
            print(treeNode.right);
        }
    }
}
