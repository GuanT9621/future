package leetcode;

/**
 * 两两交换链表中的节点
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * 思路1 其实在看到示意图后有一个想法，讲链表拆成两个链表，然后错位折叠即可。效率差
 * 思路2 建一个新head，每次取两个，然后就开始转换，添加到新head。效率高
 * 思路3 递归，很普通地进行反转即可。效率高 最优
 */
public class N24_m {
    public ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(0, head);
        ListNode temp = pre;
        while(temp.next != null && temp.next.next != null) {
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return pre.next;
    }



}
