package thread.status;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.LockSupport;
/**
 * WAITING: 等待状态，处于等待状态的线程是由于执行了3个方法中的任意方法。
 *          1. Object的wait
 *          2. Thread的join
 *          3. LockSupport 的 park。
 *          4. lock 的 lock()，在未获取到锁时等待
 *          5. FutureTask 的 get()，在未获取到执行结果时
 *
 *          处于waiting状态的线程会等待另外一个线程处理特殊的行为。
 *          再举个例子，如果一个线程调用了一个对象的wait方法，那么这个线程就会处于waiting状态直到另外一个线程调用这个对象的notify或者notifyAll方法后才会解除这个状态
 *
 * TIMED_WAITING: 有等待时间的等待状态
 *          比如调用了以下几个方法中的任意方法，并且指定了等待时间，线程就会处于这个状态。
 *          1. Thread.sleep
 *          2. Object 的 wait，带有时间
 *          3. Thread.join，带有时间
 *          4. LockSupport 的 parkNanos，带有时间
 *          5. LockSupport 的 parkUntil，带有时间
 *          6. lock 的 tryLock() 带有时间
 *          7. FutureTask 的 get() 带有时间
 *
 */
public class TransferWait {

    public static void main(String[] args) throws InterruptedException {
        Integer l = 2;

        // 用来给 a 测试 join 时的状态
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) { e.printStackTrace(); }
        });
        Thread t2 = new Thread(() -> {
            try { Thread.sleep(3 * 1000); } catch (InterruptedException e) { e.printStackTrace(); }
        });

        Thread a = new Thread(() -> {
            try {
                System.out.println("\n LockSupport.park()");
                // 这里会导致该线程处于 WAITING 状态
                LockSupport.park();

                System.out.println("\n LockSupport.parkNanos(2 000 000000);");
                // 这里会导致该线程处于 TIMED_WAITING 状态
                LockSupport.parkNanos(2 * 1000* 1000000);

                System.out.println("\n Thread.sleep()");
                // 这里会导致该线程处于 TIMED_WAITING 状态
                Thread.sleep(2 * 1000);

                System.out.println("\n Object.wait()");
                // 这里会导致该线程处于 WAITING 状态
                synchronized (l) { l.wait(); }

                System.out.println("\n Object.wait(3000)");
                // 这里会导致该线程处于 TIMED_WAITING 状态
                synchronized (l) { l.wait(2 * 1000); }

                System.out.println("\n thread.join()");
                t1.start();
                // 这里会导致该线程处于 WAITING 状态
                t1.join();

                System.out.println("\n thread.join(2000)");
                t2.start();
                // 这里会导致该线程处于 TIMED_WAITING 状态
                t2.join(2 * 1000);

            } catch (InterruptedException e) { e.printStackTrace(); }
        });

        // new 了一个线程还未 start。该线程处于 NEW 状态
        System.out.println(" un-start " + a.getState());
        a.start();

        // b 线程监控 a 线程的状态
        new Thread(() -> {
            for (int i=0; i<30; i++) {
                try {
                    Thread.State state = a.getState();
                    System.out.println("o " + state);
                    if (state.equals(Thread.State.WAITING)) {
                        synchronized (l) { l.notify(); }
                        LockSupport.unpark(a);
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }).start();

        // join 之后线程状态变为 TERMINATED
        a.join();
        System.out.println("\n a.join " + a.getState() + "\n");
    }

}
