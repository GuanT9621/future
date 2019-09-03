package thread.synchronize;


public class SynchronizedTest {

    public synchronized void method1(String name) {
        System.out.println(name + "  call method 1 start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {}
        System.out.println(name + "  call method 1 over");
    }

    public synchronized void method2(String name) {
        System.out.println(name + "  call method 2 start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {}
        System.out.println(name + "  call method 2 over");
    }

    /**
     * 两个不同的对象，方法上加锁，锁住了对象实例，所以不会产生资源的抢占。
     *
     */
    public static void main1(String[] args) {
        new Thread(() -> {
            SynchronizedTest test = new SynchronizedTest();
            test.method1("a");
        }).start();

        new Thread(() -> {
            SynchronizedTest test = new SynchronizedTest();
            test.method1("b");
        }).start();
    }

    /**
     * 同一个对象，方法上锁，锁住了对象实例，所以会产生资源的抢占。
     *
     */
    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        new Thread(() -> {
            test.method1("a");
        }).start();

        new Thread(() -> {
            test.method2("b");
        }).start();
    }

}
