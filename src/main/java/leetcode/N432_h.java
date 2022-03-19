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
        public Set<String> keys;
        public Node prev;
        public Node next;
        public int count;
        public Node() {
            this("", 0);
        }

        public Node(String key, int count) {
            this.count = count;
            keys = new HashSet<>();
            keys.add(key);
        }
        public Node insert(Node node) {
            node.prev = this;
            node.next = this.next;
            node.next.prev = node;
            node.prev.next = node;
            return node;
        }
        public void remove() {
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
                Node cur = map.get(key);
                if (cur.next.count == cur.count + 1) {
                    // 现有 Node，下一个node count 为 当前的count + 1
                    cur.next.keys.add(key);
                    map.put(key, cur.next);
                } else {
                    // 新建 Node，下一个不是当前count + 1
                    map.put(key, cur.insert(new Node(key, cur.count + 1)));
                }
                // 是否移除当前的 cur，当前node，keys 为空
                cur.keys.remove(key);
                if (cur.keys.isEmpty()) {
                    cur.remove();
                }
            } else {
                // key 不在链表
                if (root.next.count == 1) {
                    // 现有 Node，下一个node count 为 1
                    root.next.keys.add(key);
                    map.put(key, root.next);
                } else {
                    // 新建 Node 下一个不是 1
                    map.put(key, root.insert(new Node(key, 1)));
                }
            }
        }

        public void dec(String key) {
            // 测试用例保证在
            Node cur = map.get(key);
            if (cur.count == 1) {
                map.remove(key);
            } else {
                Node prev = cur.prev;
                if (prev.count == cur.count - 1) {
                    // 现有 Node，前一个node count 为 当前的count - 1
                    prev.keys.add(key);
                    map.put(key, prev);
                } else {
                    // 新建 Node，下一个不是当前count - 1
                    map.put(key, prev.insert(new Node(key, cur.count - 1)));
                }
            }
            // 是否移除 当前node，keys 为空
            cur.keys.remove(key);
            if (cur.keys.isEmpty()) {
                cur.remove();
            }
        }

        public String getMaxKey() {
            return root.prev.keys.stream().findFirst().orElse("");
        }

        public String getMinKey() {
            return root.next.keys.stream().findFirst().orElse("");
        }
    }

    public static void main(String[] args) {
        // ["AllOne","inc","inc","inc","dec","inc","inc","getMaxKey","dec","dec","dec","getMaxKey"]
        // [[],["hello"],["world"],["hello"],["world"],["hello"],["leet"],[],["hello"],["hello"],["hello"],[]]
        // [null,null,null,null,null,null,null,"hello",null,null,null,"leet"]

        AllOne allOne = new N432_h().new AllOne();
        allOne.inc("hello");
        allOne.inc("world");
        allOne.inc("hello");
        allOne.dec("world");
        allOne.inc("hello");
        allOne.inc("leet");
        System.out.println(allOne.getMaxKey());
//        System.out.println(allOne.getMinKey());
        allOne.dec("hello");
        allOne.dec("hello");
        allOne.dec("hello");
        System.out.println(allOne.getMaxKey());
//        System.out.println(allOne.getMinKey());
    }

}
