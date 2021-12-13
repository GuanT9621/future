# LRU 最近最少使用
在 redis 里缓存的淘汰策略就有用到这一个算法，今天我们自己来实现一个 LRU，并且存取时间复杂度控制在 O(1)

## 非O1复杂度
如果不限制O1复杂度，那么Map作为Cache，数组作为Key的顺序，int Max作为最大容量，完全可以实现

## O1复杂度
O(1) 时间完成查找，
1. hash - HashMap和双向链表合二为一即是LinkedHashMap
2. 双向链表，因为每次操作都是对 头或者尾进行操作 - 自己手动实现 LinkedHashMap 原理

方法一 继承 LinkedHashMap

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
      return size() > capacity;
    }


方法二 手动实现Node和Map的联系

    class LinkedNode {
        int key;
        int value;
        LinkedNode prev;
        LinkedNode next;
        public LinkedNode() {}
        public LinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer, LinkedNode> cache = new HashMap<Integer, LinkedNode>();
    private int size;
    private int capacity;
    private LinkedNode head, tail;
    
    public int get(int key) {
        LinkedNode node = cache.get(key);
        if (node == null) {
          return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        LinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            LinkedNode newNode = new LinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                LinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        } else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

