package thread.locks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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