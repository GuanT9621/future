package leetcode;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 思路：一般我们会反转，然后删除，在反转。这样效率太低。
 * 好的思路，应该有两个指针，比如删除倒数 n 个，那么指针为 1 和 n 。这样就像一个卡尺，找到了当end指针到了尾巴，那么start指针就是要删除的那个
 */
public class N19_m {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() { }
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        
        public static ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode pre = new ListNode(0, head);
            ListNode start = pre;
            ListNode end = pre;
            while (n != 0) {
                end = end.next;
                n--;
            }
            while (null != end.next) {
                end = end.next;
                start = start.next;
            }
            start.next = start.next.next;
            return pre.next;
        }

        public static void main(String[] args) {
            ListNode n5 = new ListNode(5, null);
            ListNode n4 = new ListNode(4, n5);
            ListNode n3 = new ListNode(3, n4);
            ListNode n2 = new ListNode(2, n3);
            ListNode n1 = new ListNode(1, n2);

            n1 = removeNthFromEnd(n1, 2);
            print(n1);
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
