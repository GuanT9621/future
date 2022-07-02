package algorithm.线段树;

/**
 * 线段树（动态开点）
 * 原理都一样，区别在于 add 不一定是加减，可能是乘除，可能是替换，可能是0/1
 *
 */
public class SegmentTree {

    class Node {
        // 左右节点
        Node left, right;
        // 当前节点值 懒惰标记
        int sum, add;
    }

    /**
     * 在区间 [start, end] 中更新区间 [l, r] 的值
     *
     * 下推懒惰标记 左子区间叶子节点数量 右子区间叶子节点数量
     * [start,   mid] 和 [l, r] 可能有交集，遍历左子区间
     * [mid + 1, end] 和 [l, r] 可能有交集，遍历右子区间
     * 上推结果聚合
     */
    public void update(Node node, int start, int end, int l, int r, int val) {
        if (l <= start && end <= r) {
            node.sum += (end - start + 1) * val;
            node.add = val;
            return;
        }
        int mid = (start + end) >> 1;
        pushDown(node, mid - start + 1, end - mid);
        if (l <= mid) update(node.left, start, mid, l, r, val);
        if (r > mid) update(node.right, mid + 1, end, l, r, val);
        pushUp(node);
    }

    /**
     * 在区间 [start, end] 中查询区间 [l, r] 的结果
     *
     * 把当前区间 [start, end] 均分得到左右孩子的区间范围：左子区间 [start, mid]  右子区间 [mid + 1, end]
     * 下推懒惰标记 左子区间叶子节点数量 右子区间叶子节点数量
     * [start,   mid] 和 [l, r] 可能有交集，遍历左子区间
     * [mid + 1, end] 和 [l, r] 可能有交集，遍历右子区间
     * ans 把左右子树的结果都累加起来
     */
    public int query(Node node, int start, int end, int l, int r) {
        if (l <= start && end <= r)
            return node.sum;
        int mid = (start + end) >> 1, ans = 0;
        pushDown(node, mid - start + 1, end - mid);
        if (l <= mid) ans += query(node.left, start, mid, l, r);
        if (r > mid) ans += query(node.right, mid + 1, end, l, r);
        return ans;
    }

    /** 上推计算总和 */
    private void pushUp(Node node) {
        node.sum = node.left.sum + node.right.sum;
    }

    /** 下推懒惰标记 */
    private void pushDown(Node node, int leftNum, int rightNum) {
        // 动态开点，如果 add 为 0，表示没有标记
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        if (node.add == 0) return;
        // 当前节点加上标记值 注意：需要*该子树所有叶子节点
        node.left.sum += node.add * leftNum;
        node.right.sum += node.add * rightNum;
        // 把标记下推给孩子节点
        node.left.add += node.add;
        node.right.add += node.add;
        // 取消当前节点标记
        node.add = 0;
    }

    public static void main(String[] args) {
        // 0, 20
        SegmentTree segmentTree = new SegmentTree();
        Node node = segmentTree.new Node();
        segmentTree.update(node, 0, 20, 1, 4, 1);
        segmentTree.update(node, 0, 20, 1, 8, 3);
        int query = segmentTree.query(node, 0, 20, 3, 5);
        System.out.println(query);
    }
}
