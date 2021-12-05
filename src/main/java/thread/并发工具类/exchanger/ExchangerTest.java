package thread.并发工具类.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {

    /**
     *
     * 模拟两个人以物换物。一个人制作苹果，一个人制作书籍，制作完成后，相互交换。
     *
     *      Exchanger
     *
     */
    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<String>();

        AppleMaker appleMaker = new AppleMaker(exchanger);
        BookMaker bookMaker = new BookMaker(exchanger);
        appleMaker.print();
        bookMaker.print();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(appleMaker);
        executorService.execute(bookMaker);
        executorService.shutdown();

        appleMaker.print();
        bookMaker.print();
    }

}
