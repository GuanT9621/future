package leetcode;

/**
 * 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 例如 输入：l1 = [2,4,3], l2 = [5,6,4] 输出：[7,0,8] 解释：342 + 465 = 807.
 */
public class N2 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val;this.next = next; }

        public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = new ListNode(0);
            ListNode cur = head;
            int carry = 0;
            while (null != l1 || null != l2) {
                int n1 = null == l1 ? 0 : l1.val;
                int n2 = null == l2 ? 0 : l2.val;
                int i = n1 + n2 + carry;
                int sum = i % 10;
                carry = i / 10;
                cur.next = new ListNode(sum);
                cur = cur.next;
                if (null != l1) {
                    l1 = l1.next;
                }
                if (null != l2) {
                    l2 = l2.next;
                }
            }
            if (0 != carry) {
                cur.next = new ListNode(carry);
            }
            return head.next;
        }

        public static void main(String[] args) {
            ListNode n3 = new ListNode(1, null);
            ListNode n2 = new ListNode(2, n3);
            ListNode n1 = new ListNode(7, n2);

            ListNode m3 = new ListNode(3, null);
            ListNode m2 = new ListNode(6, m3);
            ListNode m1 = new ListNode(8, m2);
            ListNode listNode = addTwoNumbers(n1, m1);
            print(listNode);
        }

        private static void print(ListNode head) {
            ListNode print = new ListNode(head.val, head.next);
            while (null != print) {
                System.out.print(print.val);
                print = print.next;
            }
            System.out.println();
        }
    }

}
