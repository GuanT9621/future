package leetcode;

/**
 *
 */
public class N729_m {

    class MyCalendar {

        class Node {
            Node l, r;
            boolean sum, add;
        }
        // 在区间 [start, end] 中更新区间 [l, r] 的值 val
        public void update(Node node, int start, int end, int l, int r, boolean val) {
            if (l <= start && end <= r) {
                node.sum = val;
                node.add = val;
                return;
            }
            int mid = (start + end) >> 1;
            pushDown(node);
            if (l <= mid) update(node.l, start, mid, l, r, val);
            if (r > mid) update(node.r, mid + 1, end, l, r, val);
            pushUp(node);
        }
        public boolean query(Node node, int start, int end, int l, int r) {
            if (l <= start && end <= r) return node.sum;
            int mid = (start + end) >> 1;
            boolean ans = false;
            pushDown(node);
            if (l <= mid) ans |= query(node.l, start, mid, l, r);
            if (r > mid) ans |= query(node.r, mid + 1, end, l, r);
            return ans;
        }
        public void pushUp(Node node) {
            node.sum = node.l.sum | node.r.sum;
        }
        public void pushDown(Node node) {
            if (node.l == null) node.l = new Node();
            if (node.r == null) node.r = new Node();
            if (!node.add) return;
            node.l.sum = node.add;
            node.r.sum = node.add;
            node.l.add = node.add;
            node.r.add = node.add;
            node.add = false;
        }

        int nodeStart = 0;
        int nodeEnd = (int) 1e9;
        Node node;

        public MyCalendar() {
            node = new Node();
        }

        public boolean book(int start, int end) {
            end -= 1;
            boolean booked = query(node, nodeStart, nodeEnd, start, end);
            if (booked) {
                return false;
            }
            update(node, nodeStart, nodeEnd, start, end, true);
            return true;
        }
    }

    public static void main(String[] args) {
        MyCalendar obj = new N729_m().new MyCalendar();
        System.out.println(obj.book(1, 3));
        System.out.println(obj.book(3, 5));
        System.out.println(obj.book(5, 7));
        System.out.println(obj.book(4, 6));
    }

}
