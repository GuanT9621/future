package jvm.classload;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoadTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };


        /**
         * 这里输出为 false 原因是系统运行 main 函数前，加载了所在的类。
         * 我们在 main 函数里手动再次使用不同的类加载器加载才满足输出 false
         *
         * 如何证明：试着加载别的类，而非 main 所在的类看看。
         *
         * 这是因为虚拟机中存在了两个ClassLoaderTest类，一个是由系统应用程序类加载器加载的，
         * 另外一个是由我们自定义的类加载器加载的，虽然都来自同一个Class文件，但依然是两个独立的类，
         * 做对象所属类型检查时结果自然为false。
         *
         */
        Object obj = myLoader.loadClass("jvm.classload.ClassLoadTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof jvm.classload.ClassLoadTest);

        Object obj2 = new ClassLoadTest();
        System.out.println(obj2.getClass());
        System.out.println(obj2 instanceof jvm.classload.ClassLoadTest);

    }


}
