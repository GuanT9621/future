package thread.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Boss implements Runnable {

    private CyclicBarrier cyclicBarrier;

    public Boss(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        System.out.println("Boss is come");

        try {
            // 自己已经准备就绪了，计数器栅栏加一
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("Boss is doing");

    }

}
