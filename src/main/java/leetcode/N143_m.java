package leetcode;

/**
 * https://leetcode-cn.com/problems/reorder-list/
 * 重排链表-链表对折
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 思路
 * 1. 通过快慢指针，一个步长为1， 一个步长为2， 找到链表中点。
 * 2. 后半段链表反转。
 * 3. 与前半段链表归并插入
 */
public class N143_m {

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = findMiddleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        // 一分为二
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    public ListNode findMiddleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public void mergeList(ListNode n1, ListNode n2) {
        ListNode tmp1;
        ListNode tmp2;
        while (n1 != null && n2 != null) {
            tmp1 = n1.next;
            tmp2 = n2.next;

            n1.next = n2;
            n1 = tmp1;

            n2.next = n1;
            n2 = tmp2;
        }
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
