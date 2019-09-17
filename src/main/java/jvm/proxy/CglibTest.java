package jvm.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib代理 基于继承的方式，也叫做 子类代理
 *      上面的静态代理和动态代理模式都是要求目标对象是实现一个接口的目标对象,
 *      但是有时候目标对象只是一个单独的对象,并没有实现任何的接口,这个时候就可以使用以目标对象子类的方式类实现代理,这种方法就叫做:Cglib代理
 *
 *
 *  Cglib子类代理实现方法:
 *      1.需要引入cglib的jar文件,但是Spring的核心包中已经包括了Cglib功能,所以直接引入spring-core-3.2.5.jar即可.
 *      2.引入功能包后,就可以在内存中动态构建子类
 *      3.代理的类不能为final,否则报错
 *      4.目标对象的方法如果为final/static,那么就不会被拦截,即不会执行目标对象额外的业务方法.
 *      5.如果方法为static,private则无法进行代理。
 */
public class CglibTest {
    /**
     * Cglib子类代理工厂
     * 对UserDao在内存中动态构建一个子类对象
     */
    public static class ProxyFactory {
        //维护目标对象
        private Object target;
        public ProxyFactory(Object target) {
            this.target = target;
        }

        //给目标对象创建一个代理对象
        public Object getProxyInstance() {
            //1.工具类
            Enhancer en = new Enhancer();
            //2.设置父类
            en.setSuperclass(target.getClass());
            //3.设置回调函数
            en.setCallback(new MethodInterceptor(){
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    System.out.println("开始事务...");
                    //执行目标对象的方法
                    Object returnValue = method.invoke(target, args);
                    System.out.println("提交事务...");
                    return returnValue;
                }
            });
            //4.创建子类(代理对象)
            return en.create();
        }
    }

    public static void main(String[] args) {
        //目标对象
        StaticProxyTest.UserDao target = new StaticProxyTest.UserDao();

        //代理对象
        StaticProxyTest.UserDao proxy = (StaticProxyTest.UserDao) new ProxyFactory(target).getProxyInstance();

        //执行代理对象的方法
        proxy.save();
    }
}
