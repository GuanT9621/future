package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * 思路 BFS 模拟
 *
 */
public class N919_m {

    class CBTInserter {
        TreeNode root = null;
        Deque<TreeNode> deque = new LinkedList<>();

        public CBTInserter(TreeNode root) {
            this.root = root;
            deque.offer(root);
            while (!deque.isEmpty()) {
                TreeNode peek = deque.peek();
                if (peek.left != null) {
                    deque.offer(peek.left);
                }
                if (peek.right != null) {
                    deque.offer(peek.right);
                }
                if (peek.left != null && peek.right != null) {
                    deque.pop();
                }
                if (peek.left == null || peek.right == null) {
                    return;
                }
            }
        }

        public int insert(int val) {
            TreeNode treeNode = new TreeNode(val);
            deque.offer(treeNode);
            TreeNode peek = deque.peek();
            if (peek.left == null) {
                peek.left = treeNode;
                return peek.val;
            }
            if (peek.right == null) {
                peek.right = treeNode;
                deque.pop();
                return peek.val;
            }
            return -1;
        }

        public TreeNode get_root() {
            return this.root;
        }
    }

}
