package leetcode.tree;

import leetcode.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * N 叉树 深度遍历
 */
public class N_DFS {

    List<Integer> ans = new ArrayList<>();

    public List<Integer> preorder(Node root) {
        ans.add(root.val);
        for (Node node : root.children) {
            if (node != null) {
                preorder(node);
            }
        }
        return ans;
    }

    public List<Integer> preorder4loop(Node root) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            ans.add(pop.val);
            List<Node> children = pop.children;
            int size = children.size();
            for (int i=size - 1; i >=0; i--) {
                stack.push(children.get(i));
            }
        }
        return ans;
    }

    public List<Integer> postorder(Node root) {
        if (root != null) {
            if (root.children != null) {
                for (Node node : root.children) {
                    postorder(node);
                }
                ans.add(root.val);
            }
        }
        return ans;
    }

    public List<Integer> postorder4loop(Node root) {
        if (root != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            Set<Node> visited = new HashSet<>();
            while (!stack.isEmpty()) {
                Node node = stack.peek();
                /* 如果当前节点为叶子节点或者当前节点的子节点已经遍历过 */
                if (node.children.size() == 0 || visited.contains(node)) {
                    stack.pop();
                    ans.add(node.val);
                    continue;
                }
                for (int i = node.children.size() - 1; i >= 0; --i) {
                    stack.push(node.children.get(i));
                }
                visited.add(node);
            }
        }
        return ans;
    }

}
