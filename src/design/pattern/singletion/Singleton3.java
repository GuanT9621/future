package design.pattern.singletion;

/**
 * 双检模式
 *
 * 因为指令重排，则需要在 instance 声明 volatile 来禁止指令重排。
 *             否则，可能返回一个没有初始化完成的instance对象。
 *
 * 指令重排：instance = new Singleton，会被编译器编译成如下JVM指令：
 *
 *                      memory = allocate();    //1：分配对象的内存空间
 *                      ctorInstance(memory);   //2：初始化对象
 *                      instance = memory;      //3：设置instance指向刚分配的内存地址
 *        其中 2 和 3 可能会颠倒顺序。
 */
public class Singleton3 {

    private volatile static Singleton3 instance = null;  //单例对象

    private Singleton3() {}  //私有构造函数

    public static Singleton3 getInstance() {
        // 只有未初始化时进入
        if (instance == null) {
            synchronized (Singleton3.class){
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }

}
