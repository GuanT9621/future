package thread.latch;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ButterCake implements Runnable {

    private List<String> cake;
    private CountDownLatch latch;

    private Object bridge1;
    private Object bridge2;

    public ButterCake(List<String> cake, CountDownLatch latch, Object bridge1, Object bridge2) {
        this.cake = cake;
        this.latch = latch;
        this.bridge1 = bridge1;
        this.bridge2 = bridge2;
    }

    @Override
    public void run() {

        try {
            synchronized (bridge1) {
                bridge1.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Start make butter");
        Random random = new Random();
        for (int i = 0; i < cake.size(); i++) {
            try {
                Thread.sleep(random.nextInt(5) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(" Make butter " + i);
            cake.set(i, "Butter " + i);
        }
        System.out.println("\nEnd make butter");

        synchronized (bridge2) {
            bridge2.notify();
        }

        latch.countDown();
    }

}
