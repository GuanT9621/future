package jvm.clinit;

/**
 * <li>类变量：被 static 修饰的非 final 变量。 形如 static int value = 123; </li>
 * 准备阶段是正式为类变量分配内存并设置类变量初始值的阶段，这些变量所使用的内存都将在方法区中进行分配。
 * 这个阶段中有两个容易产生混淆的概念需要强调一下:
 *     首先，这时候进行内存分配的仅包括类变量（被static修饰的变量)，而不包括实例变量，
 *     实例变量将会在对象实例化时随着对象一起分配在Java堆中。
 *     其次，这里所说的初始值“通常情况”下是数据类型的零值。
 *
 * <li>类常量：被 static 和 final 修饰的变量。 形如 static final int value = 123;</li>
 * 在“通常情况”下初始值是零值，那相对的会有一些“特殊情况”：
 *     如果类字段的字段属性表中存在ConstantValue属性，那在准备阶段变量value就会被初始化为ConstantValue属性所指定的值。
 *     编译时Javac将会为 value 生成ConstantValue属性，在准备阶段虚拟机就会根据ConstantValue的设置将 value 赋值为 123。
 *
 * <li>类加载 - 初始化</li>
 *     在准备阶段，变量已经赋过一次系统要求的初始值，而在初始化阶段，则根据程序员通过程序制定的主观计划去初始化类变量和其他资源，
 *     或者可以从另外一个角度来表达：初始化阶段是执行类构造器＜clinit＞（）方法的过程。
 *
 *     ＜clinit＞（）方法是由编译器自动收集类中的所有类变量的赋值动作和静态语句块（static{}块）中的语句合并产生的！
 *     编译器收集的顺序是由语句在源文件中出现的顺序所决定的！
 *     静态语句块中只能访问到定义在静态语句块之前的变量，定义在它之后的变量，在前面的静态语句块可以赋值，但是不能访问。
 *
 *     思路：可以认为 static int i = 1; 属于将变量的声明与赋值组织使用了。但在jvm里会分开执行。
 */
public class VariableInit {

    static {
        i = 2;
        System.out.println("clinit");
        // System.out.println(i);
    }

    public static int i = 1;

    // public static final int y = 123;

    public static void main(String[] args) {
        System.out.println(VariableInit.i);
        //System.out.println(VariableInit.i);
    }

}
