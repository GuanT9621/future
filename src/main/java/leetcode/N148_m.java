package leetcode;

/**
 * https://leetcode-cn.com/problems/sort-list/
 * 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 思路一：插入排序见147题
 * 思路二：归并排序。时间复杂度是O(nlogn)的排序算法包括归并排序、堆排序和快速排序（快速排序的最差时间复杂度是 O(n^2)），
 *                其中最适合链表的排序算法是归并排序。
 *                归并排序分为 1自顶向下归并排序O(nlogn) O(logn) 2自底向上归并排序O(nlogn) O(1)
 */
public class N148_m {

    /** 自底向上归并排序 */
    public static ListNode sortList(ListNode head) {
        int length = countLength(head);
        ListNode dummyHead = new ListNode(0, head);
        for (int subLength = 1; subLength<length; subLength<<=1) {
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                // 切分
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null; // head1断开
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null; // 未切分的段
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                // 合并
                prev.next = merge(head1, head2);
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }
    public static int countLength(ListNode head) {
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }
    public static ListNode merge(ListNode head1, ListNode head2) {
        ListNode mergedHead = new ListNode(), tail = mergedHead;
        ListNode tempHead1 = head1, tempHead2 = head2;
        while (tempHead1 != null && tempHead2 != null) {
            if (tempHead1.val < tempHead2.val) {
                tail.next = tempHead1;
                tempHead1 = tempHead1.next;
            } else {
                tail.next = tempHead2;
                tempHead2 = tempHead2.next;
            }
            tail = tail.next;
        }
        if (tempHead1 != null)
            tail.next = tempHead1;
        if (tempHead2 != null)
            tail.next = tempHead2;
        return mergedHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3, new ListNode(2, new ListNode(0, new ListNode(-4, null))));
        ListNode c = sortList(head);
        while (c != null) {
            System.out.println(c.val);
            c = c.next;
        }
    }
}
