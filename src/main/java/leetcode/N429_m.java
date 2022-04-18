package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 * N 叉树的层序遍历
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 */
public class N429_m {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (null == root) {
            return ans;
        }
        Deque<Node> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> line = new ArrayList<>();
            for (int i=0; i < size; i++) {
                Node poll = deque.poll();
                if (null != poll) {
                    line.add(poll.val);
                    deque.addAll(poll.children);
                }
            }
            ans.add(line);
        }
        return ans;
    }

}
