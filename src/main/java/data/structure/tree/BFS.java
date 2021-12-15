package data.structure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void print(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val);
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
    }

}
