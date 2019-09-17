package thread.exchanger;

import java.util.concurrent.Exchanger;

public class BookMaker implements Runnable {

    private String book = "book";
    private Exchanger<String> exchanger;

    public BookMaker(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {

        try {
            book = exchanger.exchange(book);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void print() {
        System.out.println("BookMaker have a " + book);
    }

}
