package leetcode;

/**
 * https://leetcode.cn/problems/range-module/
 * Range 模块
 * Range模块是跟踪数字范围的模块。设计一个数据结构来跟踪表示为 半开区间 的范围并查询它们。
 * 半开区间 [left, right) 表示所有 left <= x < right 的实数 x 。
 * 实现 RangeModule 类:
 * RangeModule() 初始化数据结构的对象。
 * void addRange(int left, int right) 添加 半开区间 [left, right)，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠的区间时，应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。
 * boolean queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返回 true ，否则返回 false 。
 * void removeRange(int left, int right) 停止跟踪 半开区间 [left, right) 中当前正在跟踪的每个实数。
 * 1 <= left < right <= 10^9    在单个测试用例中，对addRange、 queryRange和 removeRange 的调用总数不超过10^4次
 *
 * 思路 线段树（动态开点）
 * 之所以要动态开点，因为值的范围在 1e9，所需的数组大小为 4 * 1e9
 * 由于数组太大，所以我们使用链表
 * 动态开点：在「更新」或「查询」的时候动态的建立节点
 *
 */
public class N715_h {

    class RangeModule {

        class Node {
            // 左右节点
            Node left, right;
            // 当前节点值 懒惰标记
            int sum, add;
        }
        int N = (int) 1e9;
        Node root = new Node();
        // 更新
        public void update(Node node, int start, int end, int l, int r, int val) {
            int len = end - start + 1;
            // 区间节点加上更新值 注意：需要*该子树所有叶子节点 // 添加懒惰标记
            if (l <= start && end <= r) {
                node.sum += len * val;
                node.add = val;
                return;
            }
            int mid = (start + end) >> 1;
            // 下推标记
            pushDown(node, mid - start + 1, end - mid);
            // [start, mid] 和 [l, r] 可能有交集，遍历左孩子区间
            if (l <= mid)
                update(node.left, start, mid, l, r, val);
            // [mid + 1, end] 和 [l, r] 可能有交集，遍历右孩子区间
            if (r > mid)
                update(node.right, mid + 1, end, l, r, val);
            // 向上更新
            pushUp(node);
        }
        // 查询
        // 在区间 [start, end] 中查询区间 [l, r] 的结果，即 [l ,r] 保持不变
        // 对于上面的例子，应该这样调用该函数：query(root, 0, 4, 2, 4)
        public int query(Node node, int start, int end, int l, int r) {
            if (l <= start && end <= r)
                return node.sum;
            // 把当前区间 [start, end] 均分得到左右孩子的区间范围：左孩子区间 [start, mid]  左孩子区间 [mid + 1, end]
            int mid = (start + end) >> 1;
            int ans = 0;
            // 下推标记
            pushDown(node, mid - start + 1, end - mid);
            // [start, mid] 和 [l, r] 可能有交集，遍历左孩子区间
            if (l <= mid)
                ans += query(node.left, start, mid, l, r);
            // [mid + 1, end] 和 [l, r] 可能有交集，遍历右孩子区间
            if (r > mid)
                ans += query(node.right, mid + 1, end, l, r);
            // ans 把左右子树的结果都累加起来了，与树的后续遍历同理
            return ans;
        }
        // 上推计算总和
        private void pushUp(Node node) {
            node.sum = node.left.sum + node.right.sum;
        }
        // 下推懒惰标记
        private void pushDown(Node node, int leftNum, int rightNum) {
            // 动态开点，如果 add 为 0，表示没有标记
            if (node.left == null) node.left = new Node();
            if (node.right == null) node.right = new Node();
            if (node.add == 0) return;
            // 当前节点加上标记值 注意：需要*该子树所有叶子节点
            node.left.sum += node.add * leftNum;
            node.right.sum += node.add * rightNum;
            // 把标记下推给孩子节点
            node.left.add = node.add;
            node.right.add = node.add;
            // 取消当前节点标记
            node.add = 0;
        }

        public RangeModule() {

        }

        public void addRange(int left, int right) {

        }

        public boolean queryRange(int left, int right) {
            return true;
        }

        public void removeRange(int left, int right) {

        }
    }

}
