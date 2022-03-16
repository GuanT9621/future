package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/all-oone-data-structure/
 * 全 O(1) 的数据结构
 * 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。
 *
 * 实现 AllOne 类：
 * AllOne() 初始化数据结构的对象。
 * inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
 * dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。
 * getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
 *
 * 思路 HashMap + LinkedList
 * 1 看到 O(1) 就立刻想到 Hash
 * 2 要省空间，那么就是把 HashMap 的 value 组织起来，通过链表实现排序
 * 3 要取最大，最小，那么即应该是双向链表。
 */
public class N432_h {

    class Node {
        Set<String> keys;
        Node prev;
        Node next;
        int count;
        public Node() {
            this("", 0);
        }

        public Node(String key, int count) {
            this.count = count;
            keys = new HashSet<>();
            keys.add(key);
        }
        private void insert(Node node) {
            node.prev = this;
            node.next = this.next;
            node.next.prev = node;
            node.next = node;
        }
        private void remove() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
        }
    }

    class AllOne {
        HashMap<String, Node> map = new HashMap<>();
        Node root;

        public AllOne() {
            root = new Node();
            root.prev = root;
            root.next = root;
        }

        public void inc(String key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                Node next = node.next;
                if (node) {

                } else {

                }
                if (node.next != root) {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                    node.prev = node.next;
                    node.next = node.next.next;
                    node.next.next.prev = node;
                }
            } else {
                // key 不在链表
                if (root.next == root || root.next.count > 1) {
                    Node node = new Node(key, 1);
                    root.insert(node);
                    map.put(key, node);
                } else {
                    Node node = root.next;
                    node.keys.add(key);
                    map.put(key, node);
                }
            }
        }

        public void dec(String key) {
            // 测试用例保证在
            Node node = map.get(key);
            node.count--;
            if (node.count == 0) {
                node.remove();
            }
            if (node.prev != root) {
                Node prev = node.prev;
                remove(prev);
                prev.prev = node;
                prev.next = node.next;
                node.next.prev = prev;
                node.next = prev;
            }
        }



        public String getMaxKey() {
            return root.prev.string;
        }

        public String getMinKey() {
            return root.next.string;
        }
    }

}
