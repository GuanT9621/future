package thread.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://juejin.im/post/5be94044f265da61542d27d8
 *
 * 等待（以await开头系列方法）与通知（以singAll开头的系列方法)两种类型的方法，
 * 类似于Object对象的wait()与notify()/NotifyAll()方法来对线程的阻塞与唤醒。
 *
 * 在实际使用中，Condition接口实现类是AQS中的内部类ConditionObject。
 * 在其内部维护了一个FIFO(first in first out)的队列（这里我们称之为等待队列，你也可以叫做阻塞队列），通过与AQS中的同步队列配合使用，来控制获取共享资源的线程。
 *
 * 等待队列是ConditionObjec中内部的一个FIFO(first in first out)的队列，在队列中的每个节点都包含了一个线程引用，
 * 且该线程就是在ConditionObject对象上阻塞的线程。
 *
 * 需要注意的是，在等待队列中的节点是复用了AQS中Node类的定义。
 * 换句话说，在AQS中维护的同步队列与ConditionObjec中维护的等待队列中的节点类型都是AQS.Node类型。
 *
 * 在Lock锁机制下，可以拥有一个同步队列和多个等待队列
 * 与我们传统的Object监视器模型上，一个对象拥有一个同步队列和等待队列不同。
 * lock中的锁可以伴有多个条件。
 *
 */
public class ConditionTest {

    /**
     *  在该类中我们创建了两个等待队列putHand与getHand
     *  这两个等待队列的作用分别是
     *      当请数组已满时，putHand用于存储阻塞的"放入"线程，getHand用于存储阻塞的"拿"线程。
     *      需要注意的是获取一个Condition必须通过Lock的newCondition()方法。
     */


        final Lock lock = new ReentrantLock();
        final Condition putHand = lock.newCondition();
        final Condition getHand = lock.newCondition();

        final Object[] items = new Object[100];

        // 依次为，放入的角标、拿的角标、数组中放入的对象总数
        int putptr, takeptr, count;

        /**
         * 添加一个元素
         * （1）如果当前数组已满，则把当前"放入"线程，加入到"放入"等待队列中，并阻塞当前线程
         * （2）如果当前数组未满，则将x元素放入数组中，唤醒"拿"线程中的等待线程。
         */
        public void put(Object x) throws InterruptedException {
            lock.lock();
            try {
                while (count == items.length) // 如果已满，则阻塞当前"放入"线程
                    putHand.await();
                items[putptr] = x;
                if (++putptr == items.length) putptr = 0;
                ++count;
                getHand.signal(); // 唤醒"拿"线程
            } finally {
                lock.unlock();
            }
        }

        /**
         * 拿一个元素
         * （1）如果当前数组已空，则把当前"拿"线程，加入到"拿"等待队列中，并阻塞当前线程
         * （2）如果当前数组不为空，则把唤醒"放入"等待队列中的线程。
         */
        public Object take() throws InterruptedException {
            lock.lock();
            try {
                while (count == 0) // 如果为空，则阻塞当前"拿"线程
                    getHand.await();
                Object x = items[takeptr];
                if (++takeptr == items.length) takeptr = 0;
                --count;
                putHand.signal(); // 唤醒"放入"线程
                return x;
            } finally {
                lock.unlock();
            }
        }

}
