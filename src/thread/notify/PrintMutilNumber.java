package thread.notify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintMutilNumber {

    public static void main(String[] args) throws InterruptedException {

        Object lockA = new Object();
        Object lockB = new Object();
        Object lockC = new Object();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new PrintA(lockA, lockB));
        executorService.execute(new PrintB(lockB, lockC));
        executorService.execute(new PrintC(lockC, lockA));

        Thread.sleep(1);

        executorService.shutdownNow();
    }

}
