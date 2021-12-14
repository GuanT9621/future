package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lru-cache/
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶：你是否可以在O(1) 时间复杂度内完成这两种操作？
 */
public class N146_m {
    class LinkedNode {
        int key;
        int value;
        LinkedNode prev;
        LinkedNode next;
        public LinkedNode() {}
        public LinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    class LRUCache {
        private Map<Integer, LinkedNode> cache = new HashMap<>();
        private LinkedNode head, tail;
        private int size;
        private int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.head = new LinkedNode();
            this.tail = new LinkedNode();
            this.head.next = this.tail;
            this.tail.prev = this.head;
        }

        public int get(int key) {
            LinkedNode node = cache.get(key);
            if (null == node) {
                return -1;
            }
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            LinkedNode node = cache.get(key);
            if (null == node) {
                node = new LinkedNode(key, value);
                cache.put(key, node);
                addToHead(node);
                size = size + 1;
                if (size > capacity) {
                    LinkedNode tail = removeTail();
                    cache.remove(tail.key);
                    size =  size - 1;
                }
            } else {
                node.value = value;
                moveToHead(node);
            }
        }

        private void addToHead(LinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(LinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(LinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        private LinkedNode removeTail() {
            LinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }

    }
}
