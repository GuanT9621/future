package thread;

import java.util.concurrent.LinkedTransferQueue;

/**
 * jdk1.7 内部静态类 PaddedAtomicReference 比 AtomicReference 只多做了一件事：将共享变量追加到64字节。
 *          why？ 对于很多CPU L1 L2 L3缓存的高速缓存行是64个字节宽，不支持部分填充缓存行。
 *                这意味着如果队列的头节点和尾节点都不足64字节的话，处理器会将他们都读到同一个高速缓存行，在多处理器下每个处理器都会缓存同样的头、尾节点，
 *                当一个处理器试图修改头节点时，会将整个缓存行锁定，那么在缓存一致性机制的作用下，会导致其他处理器不能访问自己高速缓存中的尾节点，
 *                而队列的入队和出队操作需要不停的修改头节点和尾节点，严重影响效率。
 *
 * jdk1.8
 *
 *
 */
public class LinkedTransferQueueTest {
    public static void main(String[] args) {
        LinkedTransferQueue queue = new LinkedTransferQueue();
    }
}
