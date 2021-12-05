package thread.并发工具类.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    /**
     *
     * 模拟了一个餐馆有5张桌子，可以让5个人来吃饭，剩下的人等着别人吃完。
     * 现在来了10个人吃饭。
     *
     *      Semaphore主要用于控制当前活动线程数目。
     *
     *      Semaphore 内部基于AQS（AbstractQueuedSynchronizer）。
     *
     *
     *
     */
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(5);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Eater("eater " + i, semaphore));
        }

        executorService.shutdown();

    }

}
