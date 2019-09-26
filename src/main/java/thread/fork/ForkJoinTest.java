package thread.fork;

import java.util.concurrent.*;

public class ForkJoinTest {

    /**
     * 并行框架详解 ForkJoinPool ForkJoinTask - 有返回 RecursiveAction , 无返回 RecursiveTask
     * 维护了一个 ForkJoinTask数组和一个 ForkJoinPool worker线程池。
     *
     * forkJoinTask.fork() 时发生了什么？
     *      1 将当前线程加入 ForkJoinTask 数组中
     *      2 唤醒或创建一个 worker 执行 Task
     *
     * forkJoinTask.join() 时发生了什么？
     *      1 阻塞执行
     *      2 等待消息
     *
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /** workers数量 ： 并行性水平。默认使用{@link java.lang.Runtime#availableProcessors}。*/
        int parallelism = 10;

        /** 线程工厂 ： 创建新线程的工厂。默认使用{@link #defaultForkJoinWorkerThreadFactory}。*/
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

        RecursiveAction recursiveAction = new RecursiveAction() {
            private static final long serialVersionUID = 2603459608043734610L;
            @Override
            protected void compute() { }
        };

        RecursiveTask<Integer> recursiveTask = new RecursiveTask<Integer>() {
            private static final long serialVersionUID = -6353179214257300867L;
            @Override
            protected Integer compute() { return null; }
        };

        forkJoinPool.execute(forkJoinTask);
        forkJoinPool.execute(recursiveAction);
        ForkJoinTask<Integer> result = forkJoinPool.submit(recursiveTask);
        Integer i = result.get();
        Throwable t = result.getException();

        forkJoinTask.fork();
        forkJoinTask.join();

    }

}
