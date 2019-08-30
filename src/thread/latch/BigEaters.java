package thread.latch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BigEaters implements Runnable {

    private List<String> cakesList;
    private CountDownLatch countDownLatch;

    public BigEaters(List<String> cakesList, CountDownLatch countDownLatch) {
        this.cakesList = cakesList;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("Big eaters is awaited");
        try {
            // 等待countDownLatch计数到0，然后继续执行。否则将会一直阻塞。
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Big eaters start to eat");

        for (int i = 0; i < cakesList.size(); i++) {
            System.out.print(" Eated " + cakesList.get(i));
        }

        System.out.println("\nEnd eat");
    }

    /**
     * 生产者与消费者模式
     * 多个生产者之间相互协调
     */
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        List<String> cakesList = new ArrayList<>();
        int prepareNum = 10;
        final Object bridge1 = new Object();
        final Object bridge2 = new Object();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(new BigEaters(cakesList, countDownLatch));
        executorService.execute(new BreadCake(cakesList, countDownLatch, prepareNum, bridge1));
        executorService.execute(new ButterCake(cakesList, countDownLatch, bridge1, bridge2));
        executorService.execute(new FruitCake(cakesList, countDownLatch, bridge2));

        executorService.shutdown();
    }
}
