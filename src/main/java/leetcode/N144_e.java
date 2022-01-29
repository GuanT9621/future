package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * 二叉树的前序遍历
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 * 思路一 递归       复杂度On On
 * 思路二 迭代       复杂度On On 我们也可以用迭代的方式实现方法一的递归函数，两种方式是等价的，
 *                             区别在于递归的时候隐式地维护了一个栈，而我们在迭代的时候需要显式地将这个栈模拟出来，其余的实现都相同。
 * 思路三 Morris     复杂度On O1
 *                          1 新建临时节点，令该节点为 root；
 *                          2 如果当前节点的左子节点为空，将当前节点加入答案，并遍历当前节点的右子节点；
 *                          3 如果当前节点的左子节点不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点：
 *                              3.1 如果前驱节点的右子节点为空
 *                                  将当前节点加入答案，并将前驱节点的右子节点更新为当前节点，当前节点更新为当前节点的左子节点。
 *                              3.2 如果前驱节点的右子节点为当前节点
 *                                  将它的右子节点重新设为空，当前节点更新为当前节点的右子节点。
 *                          4 重复步骤 2 和步骤 3，直到遍历结束。
 *
 */
public class N144_e {

    // 递归
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderTraversal(result, root);
        return result;
    }
    private static void preorderTraversal(List<Integer> result, TreeNode treeNode) {
        if (treeNode == null)
            return;
        result.add(treeNode.val);
        preorderTraversal(result, treeNode.left);
        preorderTraversal(result, treeNode.right);
    }

    // 迭代
    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) { // 相当于right迭代
            while (root != null) { // 相当于left迭代
                result.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return result;
    }

    // Morris
    public static List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode p1 = root, p2 = null;
        while (p1 != null) {
            p2 = p1.left;
            // step 2
            if (p2 == null) {
                result.add(p1.val);
                p1 = p1.right;
            } else {
                // step 3 在当前节点的左子树中找到当前节点在中序遍历下的前驱节点
                while (p2.right != null && p2.right != p1) {
                    p2 = p2.right;
                }
                // step 3.1
                if (p2.right == null) {
                    result.add(p1.val);
                    p2.right = p1;
                    p1 = p1.left;
                } else {
                    // step 3.1
                    p2.right = null;
                    p1 = p1.right;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode t3 = new TreeNode(3);
        TreeNode t2 = new TreeNode(2, t3, null);
        TreeNode t1 = new TreeNode(1, null, t2);
        List<Integer> integers = preorderTraversal3(t1);
        for (Integer i : integers)
            System.out.println(i);
    }
}
