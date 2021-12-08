package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 寻找重复的子树
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 *
 * 思路一 深度优先搜索，序列化二叉树：每棵不同子树的序列化结果都是唯一的。
 *
 */
public class N652_m {
    // key 序列化的二叉树， value 该序列出现的次数
    Map<String, Integer> count = new HashMap();
    List<TreeNode> ans = new ArrayList();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        collect(root);
        return ans;
    }

    public String collect(TreeNode node) {
        if (node == null) return "#";
        String serial = node.val + "," + collect(node.left) + "," + collect(node.right);
        count.put(serial, count.getOrDefault(serial, 0) + 1);
        if (count.get(serial) == 2)
            ans.add(node);
        return serial;
    }


    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {this.val = val;this.left = left;this.right = right;}
  }

    public static void main(String[] args) {
        // [1,2,3,4,null,2,4,null,null,4]
        TreeNode l4r1 = new TreeNode(4);
        TreeNode l3r3 = new TreeNode(4);
        TreeNode l3r2 = new TreeNode(2, l4r1, null);
        TreeNode l3r1 = new TreeNode(4);
        TreeNode l2r2 = new TreeNode(3, l3r2, l3r3);
        TreeNode l2r1 = new TreeNode(2, l3r1, null);
        TreeNode l1r = new TreeNode(1, l2r1, l2r2);
        N652_m m = new N652_m();
        m.findDuplicateSubtrees(l1r);
    }
}