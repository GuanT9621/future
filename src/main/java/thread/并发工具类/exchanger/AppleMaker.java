package thread.并发工具类.exchanger;

import java.util.concurrent.Exchanger;

public class AppleMaker implements Runnable {

    private String apple = "apple";
    private Exchanger<String> exchanger;

    public AppleMaker(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            apple = exchanger.exchange(apple);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println("AppleMaker have a " + apple);
    }
}
