package thread.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Worker implements Runnable {

    private CyclicBarrier cyclicBarrier;

    public Worker(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        System.out.println("Worker is come");

        try {
            // 自己已经准备就绪了，计数器栅栏加一
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("Worker is doing");

    }

}
