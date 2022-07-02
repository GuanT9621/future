package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/find-largest-value-in-each-tree-row/
 * 在每个树行中找最大值
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 *
 * 思路 BFS
 * 要么记录当前 queue 中的 index size 来遍历一层
 * 要么取当前 queue 的 size 来遍历一层
 * 这两种效果一样，因为在每次开始遍历时，queue里只有当前一层的 node，那么 size = index size
 *
 */
public class N515_m {

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int currSize = 1;
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int nextSize = 0;
            for (int i = 0; i < currSize; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                    nextSize++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    nextSize++;
                }
            }
            ans.add(max);
            currSize = nextSize;
        }
        return ans;
    }

    public List<Integer> largestValues2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxVal = Integer.MIN_VALUE;
            while (size > 0) {
                size--;
                TreeNode node = queue.poll();
                maxVal = Math.max(maxVal, node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(maxVal);
        }
        return ans;
    }


}
