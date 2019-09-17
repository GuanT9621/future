package thread.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ReaderWriterListTest {
    ExecutorService executorService = Executors.newCachedThreadPool();
    private final static int SIZE = 100;
    private ReaderWriterList<Integer> list = new ReaderWriterList<>(SIZE, 0);

    private class Writer implements Runnable {
        private int value;
        public Writer(int value) {
            this.value = value;
        }
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    list.set(i, value);
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
            } catch (InterruptedException e) {
            }
            System.out.println("Writer finished, shutting down");
            executorService.shutdownNow();
        }
    }

    private class Reader implements Runnable {
        public void run() {
            try {
                while (! Thread.interrupted()) {
                    for (int i = 0; i < SIZE; i++) {
                        int value = list.get(i);
                        System.out.print(value);
                        TimeUnit.MILLISECONDS.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
            }
        }
    }

    public ReaderWriterListTest(int readers, int writers) {
        for (int i = 0; i < writers; i++) {
            executorService.execute(new Writer((i + 3) * 2));
        }
        for (int i = 0; i < readers; i++) {
            executorService.execute(new Reader());
        }
    }

    public static void main(String[] args) {
        new ReaderWriterListTest(1, 2);
    }

}
