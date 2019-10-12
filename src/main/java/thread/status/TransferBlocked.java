package thread.status;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BLOCKED: 阻塞状态，等待锁的释放
 *  比如线程A进入了一个synchronized方法，线程B也想进入这个方法，但是这个方法的锁已经被线程A获取了，这个时候线程B就处于BLOCKED状态
 */
public class TransferBlocked {

    static Lock lock = new ReentrantLock();
    static Integer l = 2;

    /** thread a 持有锁，b 等待锁释放时，thread b 处于 BLOCKED 状态
     *  将 synchronized 替换为 lock 后，thread b 处于 WAITING 状态
     */
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("a get lock\n");
                Thread.sleep(5 * 1000);
                System.out.println("a put lock\n");
            } catch (InterruptedException e) { e.printStackTrace();
            } finally {
                lock.unlock();
            }
//            synchronized (l) {
//                try {
//                    System.out.println("a get lock\n");
//                    Thread.sleep(5 * 1000);
//                    System.out.println("a put lock\n");
//                } catch (InterruptedException e) { e.printStackTrace(); }
//            }
        });
        Thread b = new Thread(() -> {
            // lock.tryLock(4, TimeUnit.SECONDS);
            lock.lock();
            try {
                System.out.println("b get lock\n");
                Thread.sleep(5 * 1000);
                System.out.println("b put lock\n");
            } catch (InterruptedException e) { e.printStackTrace();
            } finally {
                lock.unlock();
            }

//            synchronized (l) {
//                try {
//                    System.out.println("b get lock\n");
//                    Thread.sleep(5 * 1000);
//                    System.out.println("b put lock\n");
//                } catch (InterruptedException e) { e.printStackTrace(); }
//            }
        });

        a.start();
        b.start();
        for (int i=0; i<12; i++) {
            System.out.println("a.state " + a.getState());
            System.out.println("b.state " + b.getState());
            System.out.println();
            Thread.sleep(1000);
        }

    }

}
