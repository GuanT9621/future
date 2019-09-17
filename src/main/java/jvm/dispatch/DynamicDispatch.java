package jvm.dispatch;

/**
 *
 * 方法动态分派演示 （Method Override Resolution 方法重写决议）
 *
 * Java语言里Override(重写)
 *      我们把这种在运行期根据实际类型确定方法执行版本的分派过程称为动态分派。
 *
 */
public class DynamicDispatch {

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();

        man.sayHello();
        woman.sayHello();

        man = new Woman();
        man.sayHello();
    }
}
