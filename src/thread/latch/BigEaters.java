package thread.latch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BigEaters implements Runnable {

    private List<String> cake;
    private CountDownLatch latch;

    public BigEaters(List<String> cake, CountDownLatch latch) {
        this.cake = cake;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Big eaters is awaited");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Big eaters start to eat");

        Random random = new Random();
        for (int i = 0; i < cake.size(); i++) {
            try {
                Thread.sleep(random.nextInt(5) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(" Eated " + cake.get(i));
        }

        System.out.println("\nEnd eat");
    }

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        List<String> cake = new ArrayList<>();
        int prepare = 10;
        Object bridge1 = new Object();
        Object bridge2 = new Object();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(new BigEaters(cake, latch));
        executorService.execute(new BreadCake(cake, latch, prepare, bridge1));
        executorService.execute(new ButterCake(cake, latch, bridge1, bridge2));
        executorService.execute(new FruitCake(cake, latch, bridge2));

        executorService.shutdown();
    }
}
