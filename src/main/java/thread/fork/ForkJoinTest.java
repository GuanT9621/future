package thread.fork;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ForkJoinWorkerThread;

public class ForkJoinTest {

    /**
     * 并行框架 ForkJoinPool ForkJoinTask 详解
     *
     *
     * forkJoinTask.fork() 时发生了什么？
     *      1 将当前线程加入 ForkJoinTask 数组中
     *      2 新建或重用一个 worker 执行 Task
     *
     * forkJoinTask.join() 时发生了什么？
     *      1 阻塞执行
     *      2 等待消息
     *
     */
    public static void main(String[] args) {

        /** cpu核心数 ： 并行性水平。默认使用{@link java.lang.Runtime#availableProcessors}。*/
        int parallelism = 1;

        /** 线程工厂 ：创建新线程的工厂。默认使用{@link #defaultForkJoinWorkerThreadFactory}。*/
        ForkJoinPool.ForkJoinWorkerThreadFactory factory = new ForkJoinPool.ForkJoinWorkerThreadFactory() {
            @Override
            public ForkJoinWorkerThread newThread(ForkJoinPool pool) { return null; }
        };

        /** 异常处理者 ： 内部工作线程的处理程序，由于执行任务时遇到不可恢复的错误而终止。默认使用null。*/
        Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) { }
        };

        /** 同步模式 ：默认false后进先出。 asyncMode ? FIFO_QUEUE : LIFO_QUEUE */
        boolean asyncMode = true;

        ForkJoinPool forkJoinPool = new ForkJoinPool(parallelism, factory, handler, asyncMode);

        ForkJoinTask<Runnable> forkJoinTask = new ForkJoinTask<Runnable>() {
            private static final long serialVersionUID = -8304289335017910470L;
            @Override
            public Runnable getRawResult() { return null; }

            @Override
            protected void setRawResult(Runnable value) { }

            @Override
            protected boolean exec() { return false; }
        };

        forkJoinPool.execute(forkJoinTask);
        forkJoinTask.fork();
        forkJoinTask.join();

    }

}
