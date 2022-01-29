package leetcode;

/**
 * https://leetcode-cn.com/problems/insertion-sort-list/
 * 对链表进行插入排序
 * 给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。
 *
 * 插入排序 算法的步骤:
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *
 * 时间复杂度On^2 空间复杂度O1
 *
 */
public class N147_m {

    public static ListNode insertionSortList(ListNode head) {
        ListNode oldHead = head;
        ListNode newHead = new ListNode();
        while (oldHead != null) {
            ListNode temp = oldHead;
            oldHead = oldHead.next;
            ListNode prev = newHead;
            ListNode comp = prev.next;
            while (comp != null && comp.val < temp.val) {
                prev = prev.next;
                comp = comp.next;
            }
            temp.next = comp;
            prev.next = temp;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3, new ListNode(2, new ListNode(0, new ListNode(-4, null))));
        ListNode c = insertionSortList(head);
        while (c != null) {
            System.out.println(c.val);
            c = c.next;
        }
    }

}
