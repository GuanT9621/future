package thread.locks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReaderLock 和 WriterLock 共用一个Sync 实现读写的互斥
 *      readerLock持有资源时，能加readerLock，不能加writerLock
 *      writerLock持有资源时，不能加readerLock和writerLock
 *
 * 深入浅出下
 *
 *      ReaderLock加锁时，调用sync.acquireShared(1)，在Sync内部实现了readLockCount
 *      在Sync.tryAcquireShared()中可以发现逻辑:
 *          1. 如果另一个线程持有写锁，则失败。
 *          2. 否则，这个线程就符合锁定wrt状态的条件，所以请询问它是否应该由于队列策略而阻塞。
 *              如果没有，试着数一数。注意，step不检查可重入获取，它被推迟到完整版本，
 *              以避免在更典型的不可重入情况下检查hold count。
 *          3.如果第2步失败，要么是因为线程显然不符合条件，要么是因为CAS失败或计数饱和，则使用完整的重试循环链接到版本。
 *
 *      WriterLock加锁时，调用sync.acquire(1)，在Sync内部实现了writerLockCount
 *      在Sync.tryAcquire()中可以发现逻辑
 *          1. 如果读计数非零或写计数非零且所有者是另一个线程，则失败。(即另一个线程持有读锁或写锁，则失败)
 *          2. 如果计数饱和，则失败。(只有当count已经非零时才会发生这种情况。)
 *          3.否则，如果这个线程是可重入获取或队列策略允许，那么它就有资格获得锁。如果是，则更新状态并设置所有者。
 *
 */
public class ReaderWriterList<T> {

    private ArrayList<T> lockedList;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public ReaderWriterList(int size, T initialValue) {
        lockedList = new ArrayList<T>(Collections.nCopies(size, initialValue));
    }

    public T set(int index, T element) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            return lockedList.set(index, element);
        } finally {
            writeLock.unlock();
        }
    }

    public T get(int index) {
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            return lockedList.get(index);
        } finally {
            readLock.unlock();
        }
    }

}