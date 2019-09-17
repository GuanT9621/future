package thread.notify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用 notify 和 wait 方法实现轮流打印数字。wait(),notify()和notifyAll()都是java.lang.Object的方法：
 *
 * wait(): Causes the current thread to wait until another thread invokes the notify() method or the notifyAll() method for this object.
 * 导致当前线程等待，直到另一个线程为该对象调用notify()方法或notifyAll()方法
 *
 * notify(): Wakes up a single thread that is waiting on this object's monitor.
 * 唤醒正在此对象监视器上等待的单个线程。
 *
 * notifyAll(): Wakes up all threads that are waiting on this object's monitor.
 * 唤醒正在此对象监视器上等待的所有线程。
 *
 */
public class PrintTwoNumber implements Runnable{

    Integer number;
    static final Object lock = new Object();

    public PrintTwoNumber(Integer number) {
        this.number = number;
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.interrupted()) {
                System.out.println("PrintTwoNumber interrupted normal");
                return;
            }
            synchronized (lock) { // 得到锁
                lock.notifyAll(); // 唤醒其他线程
                try {
                    lock.wait(); // 自己等待
                } catch (InterruptedException e) {
                    System.out.println("PrintTwoNumber interrupted when lock wait() ");
                    return;
                }
                System.out.println("Number: " + number);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PrintTwoNumber printNumber1 = new PrintTwoNumber(1);
        PrintTwoNumber printNumber2 = new PrintTwoNumber(2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(printNumber1);
        executorService.execute(printNumber2);
        Thread.sleep(1);
        executorService.shutdownNow();
    }

}
