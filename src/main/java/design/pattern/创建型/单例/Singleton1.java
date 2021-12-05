package design.pattern.创建型.单例;

/**
 *
 * 饿汉模式
 *
 */
public class Singleton1 {

    private static Singleton1 instance = new Singleton1();  //单例对象

    private Singleton1() {}  //私有构造函数

    public static Singleton1 getInstance() {
        return instance;
    }

}
