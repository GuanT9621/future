package thread.pool;

import java.util.concurrent.*;

public class PoolTest {
    /** 核心线程数 ：除非设置了{allowCoreThreadTimeOut}，否则要保留在池中的线程数，即使它们是空闲的 */
    int corePoolSize = 1;

    /** 最大线程数 ：池中允许的最大线程数 */
    int maximumPoolSize = 10;

    /** 线程空闲存活时间 ：当线程数大于核心线程数时，这是多余的空闲线程在终止新任务之前等待新任务的最长时间。*/
    long keepAliveTime = 100;

    /** 线程空闲存活时间单位 ：{keepAliveTime}参数的时间单位 */
    TimeUnit unit = TimeUnit.HOURS;

    /** 阻塞队列（同步队列）：用于在任务执行前保存任务的队列。这个队列只包含{execute}方法提交的{Runnable}任务。*/
    BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);

    /** 线程工厂 ： 执行程序创建新线程时使用的工厂*/
    ThreadFactory threadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) { return null; }
    };

    /** 无法加入的线程的处理者 ： 由于达到线程边界和队列容量而阻塞执行时使用的处理程 */
    RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) { }
    };

    ThreadPoolExecutor tpe = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
            workQueue,
            threadFactory,
            handler);


    public static void main(String[] args) {
        String a = "script_version=1.2";
        System.out.println(a.split("=")[1]);
    }
}
