package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 层次化输出树结构
 */
public class S_PrintTree {

    public static List<List<Integer>> print(TreeNode treeNode) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);
        int left = 0;
        int right = 1;
        List<Integer> line = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            line.add(node.val);
            left++;
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
            // 按层存储
            if(left == right) {
                result.add(line);
                line = new ArrayList<>();
                left = 0;
                right = queue.size();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode l31 = new TreeNode(7);
        TreeNode l32 = new TreeNode(8);
        TreeNode l33 = new TreeNode(11);
        TreeNode l21 = new TreeNode(9, l31, l32);
        TreeNode l22 = new TreeNode(10, l33, null);
        TreeNode root = new TreeNode(3, l21, l22);
        List<List<Integer>> print = print(root);
        for (List<Integer> list : print) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

}
