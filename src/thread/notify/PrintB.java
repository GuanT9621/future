package thread.notify;

public class PrintB implements Runnable {

    private final Object lockB;
    private final Object lockC;

    public PrintB(Object lockB, Object lockC) {
        this.lockB = lockB;
        this.lockC = lockC;
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.interrupted()) {
                System.out.println("PrintB Interrupted");
                return;
            }

            synchronized (lockC) {
                lockC.notifyAll();
            }

            synchronized (lockB) {
                try {
                    lockB.wait();
                } catch (InterruptedException e) {
                    System.out.println("PrintB Interrupted when wait ");
                    return;
                }
            }

            System.out.println("B");
        }
    }
}
