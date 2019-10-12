package thread.status;

/**
 * BLOCKED: 阻塞状态，等待锁的释放
 *  比如线程A进入了一个synchronized方法，线程B也想进入这个方法，但是这个方法的锁已经被线程A获取了，这个时候线程B就处于BLOCKED状态
 */
public class TransferBlocked {

    static Integer l = 2;

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            synchronized (l) {
                try {
                    System.out.println("a get lock");
                    Thread.sleep(5 * 1000);
                    System.out.println("a put lock");
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });
        Thread b = new Thread(() -> {
            synchronized (l) {
                try {
                    System.out.println("b get lock");
                    Thread.sleep(5 * 1000);
                    System.out.println("b put lock");
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });

        a.start();
        b.start();
        for (int i=0; i<12; i++) {
            System.out.println("a.state " + a.getState());
            System.out.println("b.state " + b.getState());
            Thread.sleep(1000);
        }

    }

}
