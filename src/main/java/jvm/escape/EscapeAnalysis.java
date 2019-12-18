package jvm.escape;

import java.io.IOException;

/**
 * 逃逸分析 是什么？
 * 在《深入理解Java虚拟机中》关于Java堆内存有这样一段描述：
 * "但是，随着JIT编译期的发展与逃逸分析技术逐渐成熟，栈上分配、标量替换优化技术将会导致一些微妙的变化，所有的对象都分配到堆上也渐渐变得不那么“绝对”了。"
 *
 * 在编译程序优化理论中，逃逸分析是一种确定指针动态范围的方法——分析在程序的哪些地方可以访问到指针。它涉及到指针分析和形状分析。
 *
 * 编译器可以使用逃逸分析的结果作为优化的基础：
 *      将堆分配转化为栈分配。如果某个对象在子程序中被分配，并且指向该对象的指针永远不会逃逸，该对象就可以在分配在栈上，而不是在堆上。在有垃圾收集的语言中，这种优化可以降低垃圾收集器运行的频率。
 *      同步消除。如果发现某个对象只能从一个线程可访问，那么在这个对象上的操作可以不需要同步。
 *      分离对象或标量替换。如果某个对象的访问方式不要求该对象是一个连续的内存结构，那么对象的部分（或全部）可以不存储在内存，而是存储在CPU寄存器中。
 */
public class EscapeAnalysis {

    /**
     * User对象的作用域局限在方法fn中，
     * 可以使用标量替换的优化手段在栈上分配对象的成员变量，
     * 这样就不会生成User对象，大大减轻GC的压力。
     *
     * 测试逃逸分析：
     * 通过 -Xmx3G -Xmn2G -server -XX:-DoEscapeAnalysis 关闭逃逸分析运行代码，注意加大jvm内存以防止触发GC
     * 结果如下：
     *
     * guanhao@guanhaodeMacBook-Pro ~ % jps
     * 18977 KotlinCompileDaemon
     * 18196
     * 19005 Launcher
     * 19006 EscapeAnalysis
     * 19007 Jps
     * guanhao@guanhaodeMacBook-Pro ~ % jmap -histo 19006
     *
     *  num     #instances         #bytes  class name
     * ----------------------------------------------
     *    1:           644      158243936  [I
     *    2:       2000000       32000000  jvm.escape.EscapeAnalysis$User
     *
     * 打开后：
     *    2:        356503        5704048  jvm.escape.EscapeAnalysis$User
     *
     * 测试分层编译：
     * 通过 -Xmx3G -Xmn2G -server -XX:-TieredCompilation 关闭分层编译运行代码
     * 结果如下：
     *   4:         12126         194016  jvm.escape.EscapeAnalysis$User
     *
     * 关闭了分层编译之后，在Java堆上分配的User对象降低到1w多个，分层编译对逃逸分析还是有影响的。
     *
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        int sum = 0;
        int count = 1000000;
        //warm up
        for (int i = 0; i < count ; i++) {
            sum += fn(i);
        }
        Thread.sleep(500);
        for (int i = 0; i < count ; i++) {
            sum += fn(i);
        }
        System.out.println(sum);
        System.in.read();
    }

    private static int fn(int age) {
        User user = new User(age);
        int i = user.getAge();
        return i;
    }

    static class User {
        private final int age;

        public User(int age) {
            this.age = age;
        }
        public int getAge() {
            return age;
        }
    }

}
