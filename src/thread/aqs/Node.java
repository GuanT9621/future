package thread.aqs;


import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * https://juejin.im/post/5bbf04d5f265da0ad947f05b
 *
 * AQS添加尾节点
 *      当一个线程成功获取了同步状态（或者锁），其他线程无法获取到同步状态，这个时候会将该线程构造成Node节点，
 *      并加入到同步队列中，而这个加入队列的过程必须要确保线程安全，
 *      所以在AQS中提供了一个基于CAS的设置尾节点的方法：
 *          compareAndSetTail(Node expect, Node update)，
 *          它需要传递当前线程“认为”的尾节点和当前节点，只有设置成功后，当前节点才正式与之前的尾节点建立关联。
 *
 * AQS添加头节点
 *      在AQS中的同步队列中，头节点是获取同步状态成功的节点，
 *      头节点的线程会在释放同步状态时，将会唤醒其下一个节点，
 *      而下一个节点会在获取同步状态成功时将自己设置为头节点
 *
 */
public final class Node {

    static final Node SHARED = new Node();
    static final Node EXCLUSIVE = null;

    // 0 初始化状态。

    // 1 被中断或获取同步状态超时的线程将会被置为当前状态，且该状态下的线程不会再阻塞。
    static final int CANCELLED = 1;

    // -1 当前节点的线程如果释放了或取消了同步状态，将会将当前节点的状态标志位SINGAL，用于通知当前节点的下一节点，准备获取同步状态。
    static final int SIGNAL = -1;

    // -2 当前节点在Condition中的等待队列上，其他线程调用了Condition的singal()方法后，该节点会从等待队列转移到AQS的同步队列中，等待获取同步锁。
    static final int CONDITION = -2;

    // -3 与共享式获取同步状态有关，该状态标识的节点对应线程处于可运行的状态。
    static final int PROPAGATE = -3;

    // 以上 0 1 -1 -2 -3 五种状态
    volatile int waitStatus;

    // 当前节点在同步队列中的上一个节点
    volatile Node prev;
    // 当前节点在同步队列中的下一个节点
    volatile Node next;

    // 当前转换为Node节点的线程。
    volatile Thread thread;

    // 当前节点在Condition中等待队列上的下一个节点
    Node nextWaiter;

    /**
     * Returns true if node is waiting in shared mode.
     */
    final boolean isShared() { return nextWaiter == SHARED; }

    /**
     * Returns previous node, or throws NullPointerException if null.
     * Use when predecessor cannot be null.  The null check could
     * be elided, but is present to help the VM.
     *
     * @return the predecessor of this node
     */
    final Node predecessor() throws NullPointerException {
        Node p = prev;
        if (p == null)
            throw new NullPointerException();
        else
            return p;
    }

    Node() { }    // Used to establish initial head or SHARED marker

    Node(Thread thread, Node mode) {     // Used by addWaiter
        this.nextWaiter = mode;
        this.thread = thread;
    }

    Node(Thread thread, int waitStatus) { // Used by Condition
        this.waitStatus = waitStatus;
        this.thread = thread;
    }

}
