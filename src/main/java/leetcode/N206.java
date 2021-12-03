package leetcode;

/**
 * @author guanhao
 * @date 2021/11/22
 **/
public class N206 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() { }
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        /**
         * 解题思路
         * 方法一： 迭代。交换1,2节点，交换2,3节点，类推。O(N方)
         * 方法二： 迭代。取出第一个，后面的依次先前补位，最后将第一个放在最后一个，类推。O(N方)
         * 方法三： 递归。将链表不停的二分下去，最后每个链表只剩两个，然后一直翻转，到根部。O( N )
         * 方法四： 栈。新建一个Head，每次向Head添加原有链表的最后一位。O(N方)
         * 方法五： 栈。原有链表为栈，每次弹出，并入栈，可以理解为出栈入栈 (N方)
         */
        // 迭代 方法一
        public static ListNode reverseList1(ListNode head) {
            int count = 0;
            ListNode countNode = new ListNode(head.val, head.next);
            while (null != countNode) {
                countNode = countNode.next;
                count++;
            }
            System.out.println("List node count is " + count);

            int loop1 = count - 1;
            for (int i = 0; i < loop1; i++) {
                int loop2 = loop1 - i;
                ListNode pre = null;
                ListNode cur = new ListNode(head.val, head.next);
                for (int j = 0; j < loop2; j++) {
                    ListNode next = cur.next;
                    cur.next = next.next;
                    next.next = cur;
                    if (null != pre) {
                        pre.next = next;
                    }
                    pre = next;
                    if (0 == j) {
                        head = next;
                    }
                }
                print(head);
            }
            return head;
        }

        // 迭代 方法二

        // 递归 方法三

        // 栈 方法四

        // 栈 方法五
        public static ListNode reverseList5(ListNode head) {
            // 新的栈顶
            ListNode top = null;
            while (null != head) {
                ListNode nextHead = head.next;
                head.next = top;
                top = head;
                head = nextHead;
            }
            print(top);
            return top;
        }

        public static void main(String[] args) {
            ListNode n5 = new ListNode(5, null);
            ListNode n4 = new ListNode(4, n5);
            ListNode n3 = new ListNode(3, n4);
            ListNode n2 = new ListNode(2, n3);
            ListNode n1 = new ListNode(1, n2);

            ListNode cur = n1;
            print(cur);

            cur = reverseList5(n1);
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
