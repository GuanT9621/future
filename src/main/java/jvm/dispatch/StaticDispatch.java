package jvm.dispatch;

/**
 *
 * 方法静态分派演示 （Method Overload Resolution 方法重载决议）
 *
 * Java语言里的方法重载
 *      所有依赖静态类型来定位方法执行版本的分派动作称为静态分派。
 *
 */
public class StaticDispatch {

    static abstract class Human { }

    static class Man extends Human { }

    static class Woman extends Human { }

    public void sayHello(Human guy) { System.out.println("hello, guy!"); }

    public void sayHello(Man guy) { System.out.println("hello, gentleman!"); }

    public void sayHello(Woman guy) { System.out.println("hello, lady!"); }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();

        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);

        // 实际类型变化
        man = new Woman();
        // 静态类型变化
        sr.sayHello((Woman) man);
        sr.sayHello((Man) man);
    }

}