package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * use CountDownLatch Documents
 *
 */
public class CountDownLatchDemo1 {

    static Driver driver;
    static Driver2 driver2;

    CountDownLatchDemo1() {
       driver = new Driver();
       driver2 = new Driver2();
    }

    public static void main(String[] args) throws InterruptedException {
        new CountDownLatchDemo1();
//        driver.main();
        driver2.main();
    }

    class Driver { // ...
        void main() throws InterruptedException {
            int N = 10;
            CountDownLatch startSignal = new CountDownLatch(1);
            CountDownLatch doneSignal = new CountDownLatch(N);

            for (int i = 0; i < N; ++i) // create and start threads
                new Thread(new Worker(startSignal, doneSignal)).start();

            System.out.println("already start together worker"); // don't let run yet
            startSignal.countDown();      // let all threads proceed
            System.out.println("worker working ... "); // threads running
            doneSignal.await();           // wait for all to finish
            System.out.println("finish, go on"); // go on
        }
    }

    class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;
        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }
        public void run() {
            try {
                startSignal.await();
                doWork();
                doneSignal.countDown();
            } catch (InterruptedException ex) {} // return;
        }

        void doWork() { System.out.println("Start my work"); }
    }

    class Driver2 { // ...
        void main() throws InterruptedException {
            int N = 10;
            CountDownLatch doneSignal = new CountDownLatch(N);

            ExecutorService es = Executors.newFixedThreadPool(N);

            for (int i = 0; i < N; ++i) // create and start threads
                es.execute(new WorkerRunnable(doneSignal, i));

            doneSignal.await();           // wait for all to finish
            System.out.println("finish, go on"); // go on

            es.shutdown(); // don`t forgot shutdown thread pool
        }
    }

    class WorkerRunnable implements Runnable {
        private final CountDownLatch doneSignal;
        private final int i;
        WorkerRunnable(CountDownLatch doneSignal, int i) {
            this.doneSignal = doneSignal;
            this.i = i;
        }
        public void run() {
            doWork(i);
            doneSignal.countDown();
        }

        void doWork(int i) { System.out.println("step " + i); }
    }
}
