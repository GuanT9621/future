package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/map-sum-pairs/
 * 键值映射
 * 设计一个 map ，满足以下几点:
 * 字符串表示键，整数表示值
 * 返回具有前缀等于给定字符串的键的值的总和
 * 实现一个 MapSum 类：
 *
 * MapSum() 初始化 MapSum 对象
 * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对 key-value 将被替代成新的键值对。
 * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 *
 * 思路一 暴力哈希
 * 思路二 字典树
 */
public class N677_m {

    static class MapSum {
        static class Node {
            char c;
            int val;
            Node[] tree;

            public Node(char c, int val) {
                this.c = c;
                this.val = val;
                this.tree = new Node[26];
            }
        }

        Node root;

        public MapSum() {
            root = new Node(' ', 0);
        }

        public void insert(String key, int val) {
            Node curr = root;
            char[] chars = key.toCharArray();
            for (int i=0; i<chars.length; i++) {
                char c = chars[i];
                int index = c - 'a';
                if (curr.tree[index] == null) {
                    curr.tree[index] = new Node(c, 0);
                }
                curr = curr.tree[index];
                if (i == chars.length-1) {
                    curr.val = val;
                }
            }
        }

        public int sum(String prefix) {
            char[] chars = prefix.toCharArray();
            Node curr = root;
            for (char c : chars) {
                int index = c - 'a';
                curr = curr.tree[index];
                if (curr == null) {
                    return 0;
                }
            }
            int ans = 0;
            Deque<Node> deque = new LinkedList<>();
            deque.push(curr);
            while (!deque.isEmpty()) {
                Node pop = deque.pop();
                ans += pop.val;
                for (Node node : pop.tree) {
                    if (node != null) {
                        deque.push(node);
                    }
                }
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        int ap1 = mapSum.sum("apple");// 返回 3 (apple = 3)
        System.out.println(ap1);
        mapSum.insert("app", 2);
        int ap2 = mapSum.sum("ap");           // 返回 5 (apple + app = 3 + 2 = 5)
        System.out.println(ap2);
    }

}
