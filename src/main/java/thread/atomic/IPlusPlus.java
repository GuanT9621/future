package thread.atomic;

/**
 * 输出结果不为50000
 * 因为 volatile 仅仅保证对单个volatile变量的读/写具有原子性
 * 所以x++这种先读取，在计算，在写入的操作，并不能依靠volatile获得原子性。需要依靠synchronized (IPlusPlus.class) {} 锁定方法块实现原子性。
 */
public class IPlusPlus {

    static volatile int x, y;

    static class IncThread extends Thread {
        public void run() {
            for (int i = 0; i < 50000; i++) {
                x++;
                y = y + 1;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new IncThread();
        Thread t2 = new IncThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.printf("x = %d, y = %d%n", x, y);
    }

}
