package design.pattern.行为型.观察者;

/**
 * 观察者模式
 *  当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。
 *  比如，当一个对象被修改时，则会自动通知它的依赖对象。
 *  观察者模式属于行为型模式。
 *
 *
 */
public class MainTest {

    public static void main(String[] args) {

        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("\n First state change: 15");
        subject.setState(15);

        System.out.println("\n Second state change: 10");
        subject.setState(10);
    }

}
