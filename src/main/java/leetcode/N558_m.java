package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 二进制矩阵中的所有元素不是 0 就是 1 。
 * 给你两个四叉树，quadTree1 和 quadTree2。其中 quadTree 表示一个 n * n 二进制矩阵。
 * 请你返回一个表示 n * n 二进制矩阵的四叉树，它是 quadTree1 和 quadTree2 所表示的两个二进制矩阵进行 按位逻辑或运算 的结果。
 * 注意，当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制 接受 。
 * 四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
 * val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False；
 * isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
 *
 * 思路 递归 + 上推
 * 递归边界 isLeaf & isLeaf == true
 *
 */
public class N558_m {

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;
    }

    class Solution {

        public Node intersect(Node n1, Node n2) {
            Node ans = new Node();
            if (n1.isLeaf & n2.isLeaf) {
                ans.isLeaf = true;
                ans.val = n1.val | n2.val;
                return ans;
            }
            ans.topLeft = intersect(n1.isLeaf ? n1 : n1.topLeft, n2.isLeaf ? n2 : n2.topLeft);
            ans.topRight = intersect(n1.isLeaf ? n1 : n1.topRight, n2.isLeaf ? n2 : n2.topRight);
            ans.bottomLeft = intersect(n1.isLeaf ? n1 : n1.bottomLeft, n2.isLeaf ? n2 : n2.bottomLeft);
            ans.bottomRight = intersect(n1.isLeaf ? n1 : n1.bottomRight, n2.isLeaf ? n2 : n2.bottomRight);
            boolean isLeaf = ans.topLeft.isLeaf & ans.topRight.isLeaf & ans.bottomLeft.isLeaf & ans.bottomRight.isLeaf;
            boolean same = (ans.topLeft.val & ans.topRight.val & ans.bottomLeft.val & ans.bottomRight.val)
                    || !(ans.topLeft.val | ans.topRight.val | ans.bottomLeft.val | ans.bottomRight.val);
            if (isLeaf && same) {
                ans.isLeaf = true;
                ans.val = ans.topLeft.val;
                ans.topLeft = null;
                ans.topRight = null;
                ans.bottomLeft = null;
                ans.bottomRight = null;
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        // [[0,1],[1,1],[1,1],[1,0],[1,0]];
        Node q1Tl = new Node(); q1Tl.isLeaf = true; q1Tl.val = true;
        Node q1Tr = new Node(); q1Tr.isLeaf = true; q1Tr.val = true;
        Node q1Bl = new Node(); q1Bl.isLeaf = true; q1Bl.val = false;
        Node q1Br = new Node(); q1Br.isLeaf = true; q1Br.val = false;
        Node quadTree1 = new Node(); quadTree1.isLeaf = false; quadTree1.val = true;
        quadTree1.topLeft = q1Tl; quadTree1.topRight = q1Tr; quadTree1.bottomLeft = q1Bl; quadTree1.bottomRight = q1Br;

        // [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]];

        Node q3Tl = new Node(); q3Tl.isLeaf = true; q3Tl.val = false;
        Node q3Tr = new Node(); q3Tr.isLeaf = true; q3Tr.val = false;
        Node q3Bl = new Node(); q3Bl.isLeaf = true; q3Bl.val = true;
        Node q3Br = new Node(); q3Br.isLeaf = true; q3Br.val = true;

        Node q2Tl = new Node(); q2Tl.isLeaf = true; q2Tl.val = true;
        Node q2Tr = new Node(); q2Tr.isLeaf = false; q2Tr.val = true;
        q2Tr.topLeft = q3Tl; q2Tr.topRight = q3Tr; q2Tr.bottomLeft = q3Bl; q2Tr.bottomRight = q3Br;
        Node q2Bl = new Node(); q2Bl.isLeaf = true; q2Bl.val = true;
        Node q2Br = new Node(); q2Br.isLeaf = true; q2Br.val = false;

        Node quadTree2 = new Node(); quadTree2.isLeaf = false; quadTree2.val = true;
        quadTree2.topLeft = q2Tl; quadTree2.topRight = q2Tr; quadTree2.bottomLeft = q2Bl; quadTree2.bottomRight = q2Br;

        Node intersect = new N558_m().new Solution().intersect(quadTree1, quadTree2);
        Deque<Node> nodeDeque = new LinkedList<>();
        nodeDeque.offer(intersect);
        while (!nodeDeque.isEmpty()) {
            Node poll = nodeDeque.poll();
            System.out.printf("[%s,%s] ", poll.isLeaf ? 1 : 0, poll.val ? 1 : 0);
            if (poll.topLeft != null) {
                nodeDeque.offer(poll.topLeft);
            }
            if (poll.topRight != null) {
                nodeDeque.offer(poll.topRight);
            }
            if (poll.bottomLeft != null) {
                nodeDeque.offer(poll.bottomLeft);
            }
            if (poll.bottomRight != null) {
                nodeDeque.offer(poll.bottomRight);
            }
        }
    }

}
