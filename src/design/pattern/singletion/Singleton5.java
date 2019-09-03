package design.pattern.singletion;

/**
 * 枚举模式
 *          可以阻止使用反射机制来创建对象破坏单例。
 *
 */
public enum Singleton5 {
    INSTANCE;

    public void otherMethods(){
        System.out.println("Something");
    }

    public static void main(String[] args) {
        Singleton5 instance1 = Singleton5.INSTANCE;
        instance1.otherMethods();

        Singleton5 instance2 = Singleton5.INSTANCE;
        instance2.otherMethods();

        // Condition is  always 'true'
        System.out.println(instance1 == instance2);
    }

}
