package thread.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ButterCake implements Runnable {

    private List<String> cakesList;
    private CountDownLatch countDownLatch;

    private Object bridge1;
    private Object bridge2;

    public ButterCake(List<String> cakesList, CountDownLatch countDownLatch, Object bridge1, Object bridge2) {
        this.cakesList = cakesList;
        this.countDownLatch = countDownLatch;
        this.bridge1 = bridge1;
        this.bridge2 = bridge2;
    }

    @Override
    public void run() {

        // 等待上一个生产者完成，来通知还在等待（阻塞）的bridge1对象继续。
        // 一定要注意！要在上一个生产者前获取到bridge2，并进入等待状态，否则上一个生产者在通知时，并没有已等待的对象收到消息。
        synchronized (bridge1) {
            try {
                bridge1.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 上一个生产者已完成，自己开始生产
        System.out.println("Start make butter");
        for (int i = 0; i < cakesList.size(); i++) {
            System.out.print(" Make butter " + i);
            cakesList.set(i, "Butter " + i);
        }
        System.out.println("\nEnd make butter");

        // 自己生产结束，通知下一个还在等待（阻塞）的生产者可以开始了。
        synchronized (bridge2) {
            bridge2.notify();
        }

        // 自己生产结束，计数栅栏减一
        countDownLatch.countDown();
    }

}
