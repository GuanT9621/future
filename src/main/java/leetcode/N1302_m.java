package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author guanhao02
 * @date 2022/8/17
 */
public class N1302_m {

    /**
     * simple bfs
     */
    class Solution {

        public int deepestLeavesSum(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            Queue<TreeNode> ans = new LinkedList<>();
            while (!queue.isEmpty()) {
                ans.clear();
                ans.addAll(queue);
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    if (poll.left != null)
                        queue.offer(poll.left);
                    if (poll.right != null)
                        queue.offer(poll.right);
                }
            }
            int sum = 0;
            for (TreeNode an : ans) {
                sum += an.val;
            }
            return sum;
        }

    }

    /**
     * simple bfs
     */
    class Solution2 {

        public int deepestLeavesSum(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int sum = 0;
            while (!queue.isEmpty()) {
                sum = 0;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    sum += poll.val;
                    if (poll.left != null)
                        queue.offer(poll.left);
                    if (poll.right != null)
                        queue.offer(poll.right);
                }
            }
            return sum;
        }

    }

}
