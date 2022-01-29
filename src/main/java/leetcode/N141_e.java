package leetcode;


import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * 环形链表
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 *
 * 思路一：双指针，一个快一个慢，如同操场跑圈，快的一方总会赶上慢的一方。时间On 空间O1
 * 思路二：哈希表，将遍历过的放入哈希表，下一个判断表中有无。 时间On 空间On
 */
public class N141_e {

    /** 双指针 */
    public static boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null) {
            fast = fast.next == null ? null : fast.next.next;
            slow = slow.next;
            if (fast == slow && fast != null) {
                return true;
            }
        }
        return false;
    }

    /** hash */
    public static boolean hasCycle2(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        while (head != null) {
            if (!seen.add(head))
                return true;
            head = head.next;
        }
        return false;
    }


    public static void main(String[] args) {
        ListNode pos = new ListNode(2);
        pos.next = new ListNode(0, new ListNode(-4, pos));
        ListNode head = new ListNode(3, pos);
        boolean b = hasCycle2(head);
        System.out.println(b);
    }

}
