package jvm.object;

/**
 * java常量池技术:
 *   java中的常量池技术，是为了方便快捷地创建某些对象而出现的，当需要一个对象时，就可以从池中取一个出来（如果池中没有则创建一个），
 *   则在需要重复创建相等变量时节省了很多时间。常量池其实也就是一个内存空间，常量池存在于方法区中。
 *
 * String类也是java中用得多的类，同样为了创建String对象的方便，也实现了常量池的技术。
 *
 * 在开始试验前需要明确以下几点：
 *   1 常量池(constant pool)指的是在编译期被确定，并被保存在已编译的.class文件中的一些数据。
 *     它包括了关于类、方法、接口等中的常量，也包括字符串常量。
 *   2 String 对象底层是数组，所以 String 对象不可以被修改，每次修改都是新建&替换。
 *   3 new 一个对象，该对象会在堆上分配内存，该对象不是常量，不能在编译期就确定。
 *   4 == 对比的是对象的地址， equals 对比的是对象的内容（其实默认还是对比地址，只是被复写了）
 *
 */
public class StringTest {

    public static void main(String[] args) {
        /**
         * 直接赋值常量，都是从常量池中创建新的常量或寻找已有的常量。
         * 这跟(1)中创建指令有很大的不同，此时局部变量a2存储的是早已创建好的拘留字符串的堆地址。
         */
        String a1 = "G" + "H";
        String a2 = "GH";
        String a3 = "GH";
        String a4 = "";

        /**
         * 使用 new 在堆上申请内存建立对象
         * 通过下面测试可以发现堆上的每个对象地址是完全不同的。
         *
         * (1)事实上，在运行这段指令之前，JVM就已经为"GH"在堆中创建了一个拘留字符串
         * ( 值得注意的是：如果源程序中还有一个"GH"字符串常量，那么他们都对应了同一个堆中的拘留字符串)。
         * 然后用这个拘留字符串的值来初始化堆中用new指令创建出来的新的String对象，局部变量b1实际上存储的是new出来的堆对象地址。
         */
        String b1 = new String("GH");
        String b2 = new String("GH");
        String b3 = new String();
        String b4 = new String("");

        /**
         * String的intern()方法是扩充常量池的唯一一个方法；
         * 当一个String实例S调用intern()方法时，Java查找常量池中是否有相同Unicode的字符串常量，
         * 如果有，则返回其的引用，如果没有，则在常量池中增加一个Unicode等于S的字符串并返回它的引用
         */
        String c1 = b2.intern();

        System.out.println("a1 == a2 " + (a1 == a2));
        System.out.println("a2 == a3 " + (a2 == a3));
        System.out.println("a1 == GH " + (a1 == "GH"));

        System.out.println("a1 == b4 " + (a4 == b4));

        System.out.println("b1 == b2 " + (b1 == b2));
        System.out.println("b3 == b4 " + (b3 == b4));
        System.out.println("a4 == b3 " + (a4 == b3));
        System.out.println("a4 == b4 " + (a4 == b4));

        System.out.println("c1 == a2 " + (c1 == a2));
        System.out.println("c1 == b2 " + (c1 == b2));

    }

}
