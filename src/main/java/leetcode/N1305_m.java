package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees/
 * 两棵二叉搜索树中的所有元素
 * 给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。
 *
 * 思路 中序遍历 + 归并
 *
 */
public class N1305_m {
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        dfs(root1, list1);
        dfs(root2, list2);
        List<Integer> ans = new ArrayList<>();
        int i1 = 0;
        int i2 = 0;
        while (i1 < list1.size() && i2 < list2.size()) {
            if (list1.get(i1) > list2.get(i2)) {
                ans.add(list2.get(i2));
                i2++;
            } else {
                ans.add(list1.get(i1));
                i1++;
            }
        }
        if (i1 < list1.size()) {
            ans.addAll(list1.subList(i1, list1.size()));
        }
        if (i2 < list2.size()) {
            ans.addAll(list2.subList(i2, list2.size()));
        }
        return ans;
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }

}
