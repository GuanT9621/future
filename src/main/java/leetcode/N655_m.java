package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/print-binary-tree/
 * 输出二叉树
 * 在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：
 *
 * 行数m应当等于给定二叉树的高度。
 * 列数n应当总是奇数。
 * 根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。根节点所在的行与列会将剩余空间划分为两部分（左下部分和右下部分）。你应该将左子树输出在左下部分，右子树输出在右下部分。左下和右下部分应当有相同的大小。即使一个子树为空而另一个非空，你不需要为空的子树输出任何东西，但仍需要为另一个子树留出足够的空间。然而，如果两个子树都为空则不需要为它们留出任何空间。
 * 每个未使用的空间应包含一个空的字符串""。
 * 使用相同的规则输出子树。
 */
public class N655_m {

    /**
     * 1 计算树高
     * 2 生成初始化的数组
     * 3 递归填充树节点信息
     */
    public List<List<String>> printTree(TreeNode root) {
        // 计算树高
        int height = getHeight(root);
        // 生成初始化的数组
        String[][] res = new String[height][(1 << height) - 1];
        for(String[] arr : res)
            Arrays.fill(arr,"");
        // 递归填充树节点信息
        fill(res, root, 0, 0, res[0].length);

        List<List<String>> ans = new ArrayList<>();
        for(String[] arr : res)
            ans.add(Arrays.asList(arr));
        return ans;
    }

    public void fill(String[][] res, TreeNode root, int i, int l, int r) {
        if (root == null)
            return;
        res[i][(l + r) / 2] = "" + root.val;
        fill(res, root.left, i + 1, l, (l + r) / 2);
        fill(res, root.right, i + 1, (l + r + 1) / 2, r);
    }

    public int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

}
