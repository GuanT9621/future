package concurrence.hashmap;

/**
 * 参考 https://www.cnblogs.com/jelly12345/p/14472924.html
 *
 * ConcurrentHashMap 在jdk17 和 18 的实现原理对比
 * 17 Segment + 分段锁 + 数组 + 链表结构
 * 18 Node + 数组 + CAS +  红黑树（在小于8个元素时采用链表）
 *
 * 17 定位一个元素的过程需要进行两次 Hash 操作。第一次 Hash 定位到 Segment，第二次 Hash 定位到元素所在的链表的头部。
 *
 */
public class ConcHashMap17Vs18 {
}
