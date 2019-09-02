package thread;

public class DeadLock {
    private void method1() {
        synchronized (String.class) {
            System.out.println("1 acquired lock a String class");
            sleep();

            synchronized (Integer.class) {
                System.out.println("1 acquired lock a Integer class");
            }
        }
    }

    private void method2() {
        synchronized (Integer.class) {
            System.out.println("2 acquired lock a Integer class");
            sleep();

            synchronized (String.class) {
                System.out.println("2 acquired lock a String class");
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            DeadLock deadLock = new DeadLock();
            deadLock.method1();
        }).start();

        new Thread(() ->  {
            DeadLock deadLock = new DeadLock();
            deadLock.method2();
        }).start();
    }

    private static void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
