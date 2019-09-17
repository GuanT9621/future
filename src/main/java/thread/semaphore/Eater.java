package thread.semaphore;

import java.util.concurrent.Semaphore;

public class Eater implements Runnable {

    private Semaphore semaphore;
    private String name;

    public Eater(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        System.out.println(name + " wait");
        try {
            // 请求信号量，请求不到会阻塞
            semaphore.acquire();
            System.out.println(name + " start eat");
            Thread.sleep(5000);
            System.out.println(name + " end eat");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 保证获取到的信号量总能够被释放
            semaphore.release();
        }

    }

}
