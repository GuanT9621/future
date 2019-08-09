package thread.latch;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class FruitCake implements Runnable {

    private List<String> cake;
    private CountDownLatch latch;

    private Object bridge2;

    public FruitCake(List<String> cake, CountDownLatch latch, Object bridge2) {
        this.cake = cake;
        this.latch = latch;
        this.bridge2 = bridge2;
    }

    @Override
    public void run() {
        try {
            synchronized (bridge2) {
                bridge2.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Start make fruit");
        Random random = new Random();
        for (int i = 0; i < cake.size(); i++) {
            try {
                Thread.sleep(random.nextInt(5) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(" Make fruit " + i);
            cake.set(i, "Fruit " + i);
        }
        System.out.println("\nEnd make fruit");
        latch.countDown();
    }

}
