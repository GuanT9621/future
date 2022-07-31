package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS
 *
 */
public class N1161_m {

    class Solution {

        public int maxLevelSum(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int maxSum = Integer.MIN_VALUE;
            int maxLevel = 1;
            int currLevel = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                int currSum = 0;
                currLevel++;
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    if (poll.left != null) {
                        queue.offer(poll.left);
                    }
                    if (poll.right != null) {
                        queue.offer(poll.right);
                    }
                    currSum += poll.val;
                }
                if (currSum > maxSum) {
                    maxSum = currSum;
                    maxLevel = currLevel;
                }
            }
            return maxLevel;
        }

    }

}
