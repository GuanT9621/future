package thread.countdownlatch;

import java.util.ArrayList;
import java.util.List;
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
     *
     * 模拟了三条有依赖关系的流水线，先生产面包，再抹上黄油，再抹上水果，最后将做好的蛋糕给食客。
     *
     *
     *       CountDownLatch
     *
     *       减计数方式
     *       计算为0时释放所有等待的线程
     *       计数为0时，无法重置
     *       调用countDown()方法计数减一，调用await()方法只进行阻塞，对计数没任何影响
     *       不可重复利用
     *
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
