package thread.locks;

import java.util.ArrayList;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * https://segmentfault.com/a/1190000017372067
 * AQS AbstractQueuedSynchronizer
 *      它提供了一个FIFO队列，可以看成是一个用来实现同步锁以及其他涉及到同步功能的核心组件，常见的有:ReentrantLock、CountDownLatch等。
 *      AQS是一个抽象类，主要是通过继承的方式来使用，它本身没有实现任何的同步接口，仅仅是定义了同步状态的获取以及释放的方法来提供自定义的同步组件。
 *
 *      从使用层面来说，AQS的功能分为两种：独占和共享
 *          独占锁，每次只能有一个线程持有锁，比如前面给大家演示的ReentrantLock就是以独占方式实现的互斥锁
 *          共享锁，允许多个线程同时获取锁，并发访问共享资源，比如ReentrantReadWriteLock
 *
 *      AQS的实现依赖内部的同步队列,也就是FIFO的双向队列，
 *      如果当前线程竞争锁失败，那么AQS会把当前线程以及等待状态信息构造成一个Node加入到同步队列中，同时再阻塞该线程。
 *      当获取锁的线程释放锁以后，会从队列中唤醒一个阻塞的节点(线程)。
 */
public class AQS extends AbstractQueuedSynchronizer {
    public static void main(String[] args) {
        ArrayList<String> array = new ArrayList<String>();
        array.add(1,"hello world");
    }
}
