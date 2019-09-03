package design.pattern.singletion;

/**
 * 完美方法： 枚举 + 内部类 = 内部枚举类
 *
 * 内部枚举类 提供一个实例化外部类的方法。
 *
 */
public class Singleton6 {

    public enum EnumSingle {
        INSTANCE;

        private Singleton6 instance;

        EnumSingle() {
            System.out.println("instance now");
            instance = new Singleton6();
        }

        public Singleton6 getInstance() {
            return instance;
        }
    }

    private Singleton6() {}

    public static Singleton6 getInstance() {
        /**
         * EnumSingle.INSTANCE 触发 构造函数EnumSingle()
         */
        return EnumSingle.INSTANCE.getInstance();
    }

}
