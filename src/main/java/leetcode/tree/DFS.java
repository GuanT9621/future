package leetcode.tree;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 深度优先实现方式有两种
 * 1 递归
 * 2 栈
 * 因为递归和栈都有回溯的特性！其实递归本身就隐式的实现了栈
 */
public class DFS {
    // 深度优先-前序遍历
    public static void printPre(TreeNode root) {
        if (null == root) return;
        System.out.print(root.val);
        printPre(root.left);
        printPre(root.right);
    }
    // 深度优先-中序遍历
    public static void printMid(TreeNode root) {
        if (null == root) return;
        printMid(root.left);
        System.out.print(root.val);
        printMid(root.right);
    }
    // 深度优先-后序遍历
    public static void printEnd(TreeNode root) {
        if (null == root) return;
        printEnd(root.left);
        printEnd(root.right);
        System.out.print(root.val);
    }

    // 前序遍历 栈
    public static void printPreWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            // 迭代访问节点的左孩子，并入栈
            while (treeNode != null) {
                System.out.print(treeNode.val);
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            // 如果节点没有左孩子，则弹出栈顶接地那，访问节点右孩子
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.right;
            }
        }
    }
    // 中序遍历 栈
    public static void printMidWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            // 迭代访问节点的左孩子，并入栈
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            // 如果节点没有左孩子，则弹出栈顶接地那，访问节点右孩子
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                System.out.print(treeNode.val);
                treeNode = treeNode.right;
            }
        }
    }
    // 后序遍历 栈； 后序遍历也可以将前序遍历的结果通过 addFirst 倒着存入 queue 里
    public static void printEndWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        TreeNode prev = null;
        while (treeNode != null || !stack.isEmpty()) {
            // 迭代访问节点的左孩子，并入栈
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            treeNode = stack.pop();
            if (treeNode.right == null || treeNode.right == prev) {
                System.out.print(treeNode.val);
                prev = treeNode;
                treeNode = null;
            } else {
                stack.push(treeNode);
                treeNode = treeNode.right;
            }
        }
    }
    // 后序遍历 Morris
    //Morris 遍历的核心思想是利用树的大量空闲指针，实现空间开销的极限缩减。其后序遍历规则总结如下：
    //1 新建临时节点，令该节点为 root；
    //2 如果当前节点的左子节点为空，则遍历当前节点的右子节点.
    //3 如果当前节点的左子节点不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点；
    //  如果前驱节点的右子节点为空，将前驱节点的右子节点设置为当前节点，当前节点更新为当前节点的左子节点。
    //  如果前驱节点的右子节点为当前节点，将它的右子节点重新设为空。倒序输出从当前节点的左子节点到该前驱节点这条路径上的所有节点。当前节点更新为当前节点的右子节点。
    //4 重复步骤 2 和步骤 3，直到遍历结束。
    public static List<Integer> printEndWithMorris(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        TreeNode p1 = root, p2 = null;
        while (p1 != null) {
            p2 = p1.left;
            if (p2 != null) {
                while (p2.right != null && p2.right != p1) {
                    p2 = p2.right;
                }
                if (p2.right == null) {
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    p2.right = null;
                    addPath(res, p1.left);
                }
            }
            p1 = p1.right;
        }
        addPath(res, root);
        return res;
    }
    public static void addPath(List<Integer> res, TreeNode node) {
        int count = 0;
        while (node != null) {
            ++count;
            res.add(node.val);
            node = node.right;
        }
        int left = res.size() - count, right = res.size() - 1;
        while (left < right) {
            int temp = res.get(left);
            res.set(left, res.get(right));
            res.set(right, temp);
            left++;
            right--;
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

        System.out.println("前序遍历");
        printPre(root);
        System.out.println();
        printPreWithStack(root);

        System.out.println("\n中序遍历");
        printMid(root);
        System.out.println();
        printMidWithStack(root);

        System.out.println("\n后序遍历");
        printEnd(root);
        System.out.println();
        printEndWithStack(root);
    }
}
