package thread.notify;

public class PrintC implements Runnable {

    private final Object lockC;
    private final Object lockA;

    public PrintC(Object lockC, Object lockA) {
        this.lockC = lockC;
        this.lockA = lockA;
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.interrupted()) {
                System.out.println("PrintC Interrupted");
                return;
            }

            System.out.println("C");

            synchronized (lockA) {
                lockA.notifyAll();
            }

            synchronized (lockC) {
                try {
                    lockC.wait();
                } catch (InterruptedException e) {
                    System.out.println("PrintC Interrupted when wait ");
                    return;
                }
            }
        }
    }
}
