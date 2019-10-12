package thread.call;

import java.util.concurrent.*;

/**
 * callable对调用者的影响
 */
public class CallTest {

    private static Callable callable = new Callable<Integer>() {
        @Override
        public Integer call() {
            System.out.println("call in");
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("call out");
            return 999;
        }
    };

    static FutureTask<Integer> future = new FutureTask<Integer>(callable);

    public static void main(String[] args) throws InterruptedException {

        Thread a = new Thread(() -> {
            try {
                System.out.println("in");
                // Integer i = future.get();
                Integer i = future.get(5, TimeUnit.SECONDS);
                System.out.println("out " + i);
           } catch (InterruptedException | ExecutionException | TimeoutException e) { e.printStackTrace(); }
        });

        a.start();

        new Thread(() -> {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) { e.printStackTrace(); }
            future.run();
        }).start();


        for (int i=0; i<10; i++) {
            System.out.println("o " + a.getState());
            Thread.sleep(1000);
        }

    }
}
