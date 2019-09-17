package thread.local;

/**
 * https://juejin.im/post/5ac2eb52518825555e5e06ee
 *
 * ThreadLocal
 *      设计的目的是为了能够在当前线程中有属于自己的变量，并不是为了解决并发或者共享变量的问题
 *
 */
public class ThreadLocalTest {

    /**
     * 看看ThreadLocal的注释可知他的用途是保持线程私有对象，用法是通过get set取线程私有对象。
     *
     * 原理
     *      内部维护了一个特殊的Map类 - ThreadLocalMap 类是 ThreadLocal 的一个内部类。用Entry类来进行存储
     *
     * 实现
     *      每个 Thread 维护着一个 ThreadLocalMap 的引用
     *      调用 ThreadLocal 的 set() 方法时，实际上就是往 ThreadLocalMap 设置值，key是ThreadLocal对象，值是传递进来的对象
     *      调用 ThreadLocal 的 get() 方法时，实际上就是往 ThreadLocalMap 获取值，key是ThreadLocal对象
     *      ThreadLocal本身并不存储值，它只是作为一个key来让线程从ThreadLocalMap获取value。
     *
     * ThreadLocal内存泄漏的根源
     *      由于ThreadLocalMap的生命周期跟Thread一样长，如果没有手动删除对应key就会导致内存泄漏，而不是因为弱引用。
     *
     */
    private static ThreadLocal<String> local = new ThreadLocal<>();

    public static void main(String[] args) {
        local.set("put");
        String value = local.get();

        System.out.println(value);
    }

}
