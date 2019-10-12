package thread.status;

import java.util.concurrent.locks.LockSupport;

public class TransferWait {

    public static void main(String[] args) throws InterruptedException {
        Integer l = 2;

        // 用来给 a 测试 join 时的状态
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) { e.printStackTrace(); }
        });
        Thread t2 = new Thread(() -> {
            try { Thread.sleep(3 * 1000); } catch (InterruptedException e) { e.printStackTrace(); }
        });

        Thread a = new Thread(() -> {
            try {
                System.out.println("\n LockSupport.park()");
                // 这里会导致该线程处于 WAITING 状态
                LockSupport.park();

                System.out.println("\n LockSupport.parkNanos(2 000 000000);");
                // 这里会导致该线程处于 TIMED_WAITING 状态
                LockSupport.parkNanos(2 * 1000* 1000000);

                System.out.println("\n Thread.sleep()");
                // 这里会导致该线程处于 TIMED_WAITING 状态
                Thread.sleep(2 * 1000);

                System.out.println("\n Object.wait()");
                // 这里会导致该线程处于 WAITING 状态
                synchronized (l) { l.wait(); }

                System.out.println("\n Object.wait(3000)");
                // 这里会导致该线程处于 TIMED_WAITING 状态
                synchronized (l) { l.wait(2 * 1000); }

                System.out.println("\n thread.join()");
                t1.start();
                // 这里会导致该线程处于 WAITING 状态
                t1.join();

                System.out.println("\n thread.join(2000)");
                t2.start();
                // 这里会导致该线程处于 TIMED_WAITING 状态
                t2.join(2 * 1000);

            } catch (InterruptedException e) { e.printStackTrace(); }
        });

        // new 了一个线程还未 start。该线程处于 NEW 状态
        System.out.println(" un-start " + a.getState());
        a.start();

        // b 线程监控 a 线程的状态
        new Thread(() -> {
            for (int i=0; i<30; i++) {
                try {
                    Thread.State state = a.getState();
                    System.out.println("o " + state);
                    if (state.equals(Thread.State.WAITING)) {
                        synchronized (l) { l.notify(); }
                        LockSupport.unpark(a);
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }).start();

        // join 之后线程状态变为 TERMINATED
        a.join();
        System.out.println("\n a.join " + a.getState() + "\n");
    }
}
