package leetcode;

import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 思路一 顺序合并，暴力处理
 * 思路二 分而治之，将K个链表分成一对一对合并
 * 思路二 优先队列，维护当前每个链表没有被合并的元素的最前面一个元素，每次在这些元素里面选取 val 属性最小的元素合并到答案中。
 *       在选取最小元素的时候，我们可以用优先队列来优化这个过程
 *
 */
public class N23_h {

    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;
        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }
        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Status> queue = new PriorityQueue<Status>();
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode();
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status poll = queue.poll();
            tail.next = poll.ptr;
            tail = tail.next;
            ListNode next = poll.ptr.next;
            if (next != null)
                queue.offer(new Status(next.val, next));
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode n2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode n3 = new ListNode(2, new ListNode(6));
        ListNode listNode = new N23_h().mergeKLists(new ListNode[]{n1, n2, n3});
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }

}
