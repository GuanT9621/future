package thread.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

    /**
     *
     * 模拟了老板，领导，员工三人都来公司，大家才能开始一起上班。
     *
     *
     *      CyclicBarrier
     *
     *      加计数方式
     *      计数达到指定值时释放所有等待线程
     *      计数达到指定值时，计数置为0重新开始
     *      调用await()方法计数加1，若加1后的值不等于指定值，则线程阻塞
     *      可重复利用
     *
     */
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new Leader(cyclicBarrier));
        executorService.execute(new Worker(cyclicBarrier));
        executorService.execute(new Boss(cyclicBarrier));

        executorService.shutdown();
    }
}
