package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicPlusPlus {

    static AtomicInteger x = new AtomicInteger(0);

    static class IncThread extends Thread {
        public void run() {
            for (int i = 0; i < 50000; i++) x.getAndIncrement();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new IncThread();
        t1.start();
        t1.join();
        System.out.printf("x = %d", x.get());
    }

}
