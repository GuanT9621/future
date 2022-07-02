package leetcode.tree;

import leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    // 广度优先
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
