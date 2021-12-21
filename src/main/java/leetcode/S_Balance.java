package leetcode;

/**
 * 判断一颗二叉树是否为平衡二叉树
 *
 * 同类型 N98_m
 */
public class S_Balance {

    public boolean isBalance(TreeNode root) {
        if (null == root) return true;
        //计算左节点高度
        int left = height(root.left);
        //计算右节点高度
        int right = height(root.right);
        //对比差是否不超过1
        if (Math.abs(left-right) > 1)
            return false;
        return isBalance(root.left) && isBalance(root.right);
    }
    // 计算树高
    private int height(TreeNode node) {
        if (null == node) return 0;
        int h1 = height(node.left);
        int h2 = height(node.right);
        return Math.max(h1, h2) + 1;
    }

}
