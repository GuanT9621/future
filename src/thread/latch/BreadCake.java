package thread.latch;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class BreadCake implements Runnable {

    private List<String> cake;
    private CountDownLatch latch;
    private int prepare;

    private Object bridge1;

    public BreadCake(List<String> cake, CountDownLatch latch, int prepare, Object bridge1) {
        this.cake = cake;
        this.latch = latch;
        this.prepare = prepare;
        this.bridge1 = bridge1;
    }

    @Override
    public void run() {
        System.out.println("Start make bread");
        Random random = new Random();
        for (int i = 0; i < prepare; i++) {
            try {
                Thread.sleep(random.nextInt(5) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(" Make bread " + i);
            cake.add("Bread " + i);
        }
        System.out.println("\nEnd make bread");

        synchronized (bridge1) {
            bridge1.notify();
        }

        latch.countDown();
    }

}
