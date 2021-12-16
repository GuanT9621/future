package data.structure.maps;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * HashMap 参考 https://www.jianshu.com/p/ee0de4c99f87
 *
 * ConcurrentHashMap 参考 https://www.cnblogs.com/williamjie/p/9099861.html
 */
public class Maps {

    /**
     * HashMap使用链表法避免哈希冲突（相同hash值）
     * 当链表长度大于TREEIFY_THRESHOLD（默认为8）时，将链表转换为红黑树（1.8以后优化的）
     * 当然小于UNTREEIFY_THRESHOLD（默认为6）时，又会转回链表以达到性能均衡。
     */
    Map<String, String> map = new HashMap<>();

    /**
     * HashMap和双向链表合二为一即是LinkedHashMap
     * 它是一个将所有Entry节点链入一个双向链表的HashMap
     */
    Map<String, String> map1 = new LinkedHashMap<>();

    /**
     * 1.7 分段锁的设计，只有在同一个分段内才存在竞态关系，不同的分段锁之间没有锁竞争.ConcurrentHashMap是弱一致性的
     *      并发度可以理解为程序运行时能够同时更新ConccurentHashMap且不产生锁竞争的最大线程数，
     *      实际上就是ConcurrentHashMap中的分段锁个数，即Segment[]的数组长度，默认的并发度为16
     *
     * 1.8 利用CAS算法。底层依然由 Node + “数组” + 链表 / 红黑树的方式思想
     */
    ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();
}
