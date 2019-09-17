package jvm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理本质是：基于接口,动态生成一个代理类
 *
 *  特点:
 *      1.代理对象不需要实现接口, 但是目标对象一定要实现接口,否则不能用动态代理
 *      2.代理对象的生成,是利用JDK的API, 动态的在内存中构建代理对象(需要我们指定创建代理对象/目标对象实现的接口的类型)
 *      3.动态代理也叫做: JDK代理, 接口代理
 *
 *  JDK实现代理只需要使用newProxyInstance方法,但是该方法需要接收三个参数,完整的写法是:
 *      static Object Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h )
 *
 *  代码示例:
 *  接口类IUserDao.java以及接口实现类,目标对象UserDao是一样的,没有做修改.
 *  在这个基础上,增加一个代理工厂类 (ProxyFactory.java),将代理类写在这个地方,
 *  然后在测试类(需要使用到代理的代码)中先建立目标对象和代理对象的联系,然后调用代理对象中的同名方法
 *
 */
public class DynamicProxyTest {

    /**
     * 创建动态代理对象
     * 动态代理不需要实现接口,但是需要指定接口类型
     */
    public static class ProxyFactory{
        //维护一个目标对象
        private Object target;
        public ProxyFactory(Object target){
            this.target=target;
        }

        //给目标对象生成代理对象
        public Object getProxyInstance(){
            return Proxy.newProxyInstance(
                    target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("开始事务2");
                            //运用反射执行目标对象方法
                            Object returnValue = method.invoke(target, args);
                            System.out.println("提交事务2");
                            return returnValue;
                        }
                    }
            );
        }
    }

    public static void main(String[] args) {
        // 目标对象
        StaticProxyTest.IUserDao target = new StaticProxyTest.UserDao();
        // 【原始的类型 class UserDao】
        System.out.println(target.getClass());

        // 给目标对象，创建代理对象
        StaticProxyTest.IUserDao proxy = (StaticProxyTest.IUserDao) new ProxyFactory(target).getProxyInstance();

        // class $Proxy0   内存中动态生成的代理对象
        System.out.println(proxy.getClass());

        // 执行方法   【代理对象】
        proxy.save();
    }
}
