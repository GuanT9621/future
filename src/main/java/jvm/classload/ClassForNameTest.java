package jvm.classload;

/**
 * Class.forName() 和 ClassLoader.loadClass 的区别
 *
 * Class.forName()
 *      会进行初始化
 *      JDBC 使用forName加载Driver，因为JDBC规范要求Driver必须向DriverManager注册自己
 *
 * ClassLoader.loadClass()
 *      只将类加载到JVM中
 *      Spring IOC 使用loadClass
 *
 */
public class ClassForNameTest {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("start ClassForName");
        Class.forName("jvm.classload.SubClass");

        System.out.println("start ClassLoader");
        ClassLoader.getSystemClassLoader().loadClass("jvm.classload.SubClass");
    }

}
