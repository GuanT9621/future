package thread.latch;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class BreadCake implements Runnable {

    private List<String> cakesList;
    private CountDownLatch countDownLatch;
    private int prepareNum;
    private Object bridge1;

    public BreadCake(List<String> cakesList, CountDownLatch countDownLatch, int prepareNum, Object bridge1) {
        this.cakesList = cakesList;
        this.countDownLatch = countDownLatch;
        this.prepareNum = prepareNum;
        this.bridge1 = bridge1;
    }

    @Override
    public void run() {

        // 自己开始生产
        System.out.println("Start make bread");
        for (int i = 0; i < prepareNum; i++) {
            System.out.print(" Make bread " + i);
            cakesList.add("Bread " + i);
        }
        System.out.println("\nEnd make bread");

        // 自己生产结束，通知下一个还在等待（阻塞）的生产者可以开始了。
        synchronized (bridge1) {
            bridge1.notify();
        }

        // 自己生产结束，计数栅栏减一
        countDownLatch.countDown();
    }

}
