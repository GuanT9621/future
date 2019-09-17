package thread.locks;

import java.util.concurrent.locks.LockSupport;

/**
 * https://juejin.im/post/5bdc1142e51d45052c6fede7
 *
 * LockSupport中的对外提供的方法都是静态方法。这些方法提供了最基本的线程阻塞和唤醒功能
 * 在LockSupport类中定义了一组以park开头的方法用来阻塞当前线程。以及unPark(Thread thread)方法来唤醒一个被阻塞的线程。
 *
 */
public class LockSupportTest {

    /**
     * 代码中，创建了两个线程，线程a与线程b（线程a优先运行与线程b）
     * 在线程a中，通过调用LockSupport.park("线程a的blocker数据"); 给线程a设置了一个String类型的blocker，当线程a运行的时候，直接将线程a阻塞。
     * 在线程b中，先会获取线程a中的blocker，打印输出后。再通过LockSupport.unpark(a);唤醒线程a。当唤醒线程a后。最后输出并打印线程a中的blocker。
     *
     */
    public static void main(String[] args) throws InterruptedException {
            Thread a = new Thread(new Runnable() {
                @Override
                public void run() {
                    LockSupport.park("线程a的blocker数据");
                    System.out.println("我是被线程b唤醒后的操作");
                }
            });
            a.start();

            // 让当前主线程睡眠1秒，保证线程a在线程b之前执行
            Thread.sleep(1000);

            Thread b = new Thread(new Runnable() {
                @Override
                public void run() {
                    String before = (String) LockSupport.getBlocker(a);
                    System.out.println("阻塞时从线程a中获取的blocker------>" + before);
                    LockSupport.unpark(a);

                    try {
                        // 这里睡眠是，保证线程a已经被唤醒了
                        Thread.sleep(1000);
                        String after = (String) LockSupport.getBlocker(a);
                        System.out.println("唤醒时从线程a中获取的blocker------>" + after);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            b.start();
        }

}
