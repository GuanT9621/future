package leetcode.tree;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树 深度遍历
 */
public class B_DFS {

    List<Integer> ans = new ArrayList<>();

    public void preorder(TreeNode root) {
        ans.add(root.val);
        if (root.left != null) {
            preorder(root.left);
        }
        if (root.right != null) {
            preorder(root.right);
        }
    }

    public void preorder4loop(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            ans.add(pop.val);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        root.left = t2;
        root.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.right = t6;

        B_DFS BDfs = new B_DFS();
        BDfs.preorder4loop(root);
        for (Integer an : BDfs.ans) {
            System.out.print(an + " ");
        }
    }
}
