package design.pattern.创建型.单例;

/**
 *
 * 懒汉模式
 *
 */
public class Singleton2 {

    private static Singleton2 instance = null; //单例对象

    private Singleton2() {}  //私有构造函数

    public static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }

}
