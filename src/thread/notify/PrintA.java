package thread.notify;

public class PrintA implements Runnable {

    private final Object lockA;
    private final Object lockB;

    public PrintA(Object lockA, Object lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.interrupted()) {
                System.out.println("PrintA Interrupted");
                return;
            }

            /**
             * 在调用 notify 前必须拥有对象的锁，否则会抛出 java.lang.IllegalMonitorStateException
             */
            synchronized (lockB) {
                lockB.notifyAll();
            }

            /**
             * 在调用 wait 前必须拥有对象的锁，否则会抛出 java.lang.IllegalMonitorStateException
             */
            synchronized (lockA) {
                try {
                    lockA.wait();
                } catch (InterruptedException e) {
                    System.out.println("PrintA Interrupted when wait ");
                    return;
                }
            }

            System.out.println("A");
        }
    }

}
