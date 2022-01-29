package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 * 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 不允许修改 链表。
 *
 * 解析：和141一样，只是要确定入环节点。
 *
 * 思路一：双指针，在快慢指针的情况下，相遇时，快的比慢的一定多走了一倍路径，
 *       那么 a+n(b+c)+b = 2(a+b)，那么 a = c+(n−1)(b+c)， c = a
 *       其中 a为入环前的路径，b为慢指针走的路径，c为环内剩下的路径，b+c等于环路径
 *       n为快指针走过的圈数(n=1），慢指针走过的圈数为0。
 *       复杂度 On O1
 *
 * 思路二：哈希表，第一个重复的位置元素直接返回即可。On On
 */
public class N142_m {

    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null) {
            fast = fast.next == null ? null : fast.next.next;
            slow = slow.next;
            if (fast == slow && fast != null) {
                ListNode detect = head;
                while (detect != slow) {
                    detect = detect.next;
                    slow = slow.next;
                }
                return detect;
            }
        }
        return null;
    }

    public static ListNode detectCycle2(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        ListNode tmp = head;
        while (tmp != null) {
            if (!seen.add(tmp))
                return tmp;
            tmp = tmp.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode pos = new ListNode(2);
        pos.next = new ListNode(0, new ListNode(-4, pos));
        ListNode head = new ListNode(3, pos);
        ListNode c = detectCycle(head);
        System.out.println(c.val);
    }
}
