package thread.并发工具类.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Leader implements Runnable {

    private CyclicBarrier cyclicBarrier;

    public Leader(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        System.out.println("Leader is come");

        try {
            // 自己已经准备就绪了，计数器栅栏加一
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("Leader is doing");
    }

}
