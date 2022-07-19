package leetcode;

/**
 * 思路 线段树 + 动态开点
 */
public class N731_m {

    class MyCalendarTwo {

        class Node {
            int max, add;
            Node l, r;
        }

        public void update(Node node, int start, int end, int left, int right, int val) {
            if (left <= start && end <= right) {
                // add number += , not update number =
                node.max += val;
                node.add += val;
                return;
            }
            int mid = (start + end) >> 1;
            pushDown(node);
            if (left <= mid) update(node.l, start, mid, left, right, val);
            if (right > mid) update(node.r, mid + 1, end, left, right, val);
            pushUp(node);
        }

        public int query(Node node, int start, int end, int left, int right) {
            if (left <= start && end <= right) return node.max;
            int mid = (start + end) >> 1;
            pushDown(node);
            int ans = 0;
            if (left <= mid) ans = Math.max(ans, query(node.l, start, mid, left, right));
            if (right > mid) ans = Math.max(ans, query(node.r, mid + 1, end, left, right));
            return ans;
        }

        public void pushUp(Node node) {
            node.max = Math.max(node.l.max, node.r.max);
        }

        public void pushDown(Node node) {
            if (node.l == null) node.l = new Node();
            if (node.r == null) node.r = new Node();
            if (node.add == 0) return;
            node.l.add += node.add;
            node.r.add += node.add;
            node.l.max += node.add;
            node.r.max += node.add;
            node.add = 0;
        }

        Node node;
        int start;
        int end;
        public MyCalendarTwo() {
            node = new Node();
            start = 0;
            end = (int) 1e9;
        }

        public boolean book(int left, int right) {
            right--;
            int query = query(node, start, end, left, right);
            if (query > 1) {
                return false;
            }
            update(node, start, end, left, right, 1);
            return true;
        }

    }

    public static void main(String[] args) {

        MyCalendarTwo obj = new N731_m().new MyCalendarTwo();

        // true,true,true,false,true,true
//        System.out.println(obj.book(10, 20));
//        System.out.println(obj.book(50, 60));
//        System.out.println(obj.book(10, 40));
//        System.out.println(obj.book(5, 15));
//        System.out.println(obj.book(5, 10));
//        System.out.println(obj.book(25, 55));

        // true,true,true,true,false,false,true,false,false,false
        System.out.println(obj.book(24, 40));
        System.out.println(obj.book(43, 50));
        System.out.println(obj.book(27, 43));
        System.out.println(obj.book(5, 21));
        System.out.println(obj.book(30, 40));
        System.out.println(obj.book(14, 29));
        System.out.println(obj.book(3, 19));
        System.out.println(obj.book(3, 14));
        System.out.println(obj.book(25, 39));
        System.out.println(obj.book(6, 19));
    }

}
