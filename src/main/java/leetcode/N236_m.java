package leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 思考：分两种情况
 * 1：p 在 q 的同一子树下，那么 p 为最近公共祖先。
 * 2：p 和 q 在不同子树中，那么 p 和 q 更高的一个的父节点为最近公共祖先。
 *
 * 思路一：递归 O(N) O(N)
 * 我们递归遍历整棵二叉树，定义 f(x) 表示 x 节点的子树中是否包含 p 节点或 q 节点，如果包含为 true，否则为 false。
 * 那么符合条件的最近公共祖先 x 一定满足如下条件：
 * (f(lson) && f(rson)) ∣∣ ((x=p ∣∣ x=q) && (f(lson) ∣∣ f(rson)))
 *
 * 思路二：存储父节点 O(N) O(N)
 * 我们可以用哈希表存储所有节点的父节点，然后我们就可以利用节点的父节点信息从 p 结点开始不断往上跳，并记录已经访问过的节点，
 * 再从 q 节点开始不断往上跳，如果碰到已经访问过的节点，那么这个节点就是我们要找的最近公共祖先。
 * 1 从根节点开始遍历整棵二叉树，用哈希表记录每个节点的父节点指针。
 * 2 从 p 节点开始不断往它的祖先移动，并用数据结构记录已经访问过的祖先节点。
 * 3 同样，我们再从 q 节点开始不断往它的祖先移动，如果有祖先已经被访问过，即意味着这是 p 和 q 的深度最深的公共祖先，即 LCA 节点
 *
 */
public class N236_m {

    private TreeNode ans;

    public N236_m() {
        this.ans = null;
    }

    // 递归法
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return ans;
    }

    public boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return false;
        boolean l = dfs(root.left, p, q);
        boolean r = dfs(root.right, p, q);
        if ((l && r) || ((root.val == p.val || root.val == q.val) && (l || r))) {
            ans = root;
        }
        return l || r || (root.val == p.val || root.val == q.val);
    }

    // 存储父节点
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<Integer, TreeNode> parentMap = new HashMap<>();
        dfs(root, parentMap);
        HashSet<TreeNode> visited = new HashSet<>();
        while (p != null) {
            visited.add(p);
            p = parentMap.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q)) {
                return q;
            }
            q = parentMap.get(q.val);
        }
        return null;
    }

    public void dfs(TreeNode treeNode, HashMap<Integer, TreeNode> parentMap) {
        if (treeNode == null)
            return;
        if (treeNode.left != null) {
            parentMap.put(treeNode.left.val, treeNode);
            dfs(treeNode.left, parentMap);
        }
        if (treeNode.right != null) {
            parentMap.put(treeNode.right.val, treeNode);
            dfs(treeNode.right, parentMap);
        }
    }

    public static void main(String[] args) {
        TreeNode l4_7 = new TreeNode(7);
        TreeNode l4_4 = new TreeNode(4);
        TreeNode l3_6 = new TreeNode(6);
        TreeNode l3_2 = new TreeNode(2, l4_7, l4_4);
        TreeNode l3_0 = new TreeNode(0);
        TreeNode l3_8 = new TreeNode(8);
        TreeNode l2_5 = new TreeNode(5, l3_6, l3_2);
        TreeNode l2_1 = new TreeNode(1, l3_0, l3_8);
        TreeNode l1_3 = new TreeNode(3, l2_5, l2_1);
        TreeNode treeNode = new N236_m().lowestCommonAncestor2(l1_3, l2_5, l4_4);
        System.out.println(treeNode.val);
    }

}
