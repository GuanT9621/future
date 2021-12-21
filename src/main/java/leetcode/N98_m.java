package leetcode;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 * 验证二叉搜索树
 *
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 方法一 递归
 * 方法二 中序遍历：判断是遍历结果升序的
 *
 */
public class N98_m {

    public boolean isValidBST(TreeNode root) {
//        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return isValidBST3(root, Long.MIN_VALUE);
    }

    // 递归
    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val)
                && isValidBST(node.right, node.val, upper);
    }

    // 中序遍历
    public boolean isValidBST2(TreeNode node) {
        if (node == null) {
            return true;
        }
        Long max = Long.MIN_VALUE;
        boolean result = true;
        TreeNode temp = node;
        while (temp != null && result) {
            temp = temp.right;
        }
        return result;
    }

    // 中序遍历
    public boolean isValidBST3(TreeNode node, Long max) {
        if (node == null) {
            return true;
        }
        boolean result = isValidBST3(node.left, max);
        result = result && node.val > max;
        result = result && isValidBST3(node.right, Math.max(node.val, max));
        return result;
    }

}
