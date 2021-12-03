package thread.status;

/**
 * NEW: 线程创建之后，但是还没有启动(not yet started)。这时候它的状态就是NEW
 *
 * RUNNABLE: 正在Java虚拟机下跑任务的线程的状态。在RUNNABLE状态下的线程可能会处于等待状态， 因为它正在等待一些系统资源的释放，比如IO
 *
 * BLOCKED: 阻塞状态，等待锁的释放
 *          比如线程A进入了一个synchronized方法，线程B也想进入这个方法，但是这个方法的锁已经被线程A获取了，这个时候线程B就处于BLOCKED状态
 *
 * WAITING: 等待状态，处于等待状态的线程是由于执行了3个方法中的任意方法。
 *          1. Object的wait
 *          2. Thread的join
 *          3. LockSupport 的 park。
 *          4. lock 的 lock()，在未获取到锁时等待
 *          5. FutureTask 的 get() 在未获取到返回时
 *
 *          处于waiting状态的线程会等待另外一个线程处理特殊的行为。
 *          举例，如果一个线程调用了一个对象的wait方法，那么这个线程就会处于waiting状态直到另外一个线程调用这个对象的notify或者notifyAll方法后才会解除这个状态
 *
 * TIMED_WAITING: 有等待时间的等待状态
 *              比如调用了以下几个方法中的任意方法，并且指定了等待时间，线程就会处于这个状态。
 *              1. Thread.sleep
 *              2. Object 的 wait，带有时间
 *              3. Thread.join，带有时间
 *              4. LockSupport 的 parkNanos，带有时间
 *              5. LockSupport 的 parkUntil，带有时间
 *              6. lock 的 tryLock() 带有时间
 *              7. FutureTask 的 get() 带有时间
 *
 * TERMINATED: 线程中止的状态，这个线程已经完整地执行了它的任务
 */
public class Status {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        System.out.println(thread.getState());

        // thread.interrupt(); // nothing
        // thread.join(); // nothing

        thread.start();

        // 再次start会抛出异常，因为状态已经变为 非NEW。进入 start() 源码会发现“任何非NEW的状态下调用 start 都会抛出异常”
        // thread.start(); // java.lang.IllegalThreadStateException

        System.out.println(thread.getState());

        thread.interrupt();
        thread.interrupt(); // nothing
        System.out.println(thread.getState());

        thread.join();
        thread.join(); // nothing
        System.out.println(thread.getState());

    }
}
