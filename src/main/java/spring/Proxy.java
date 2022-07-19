package spring;

import org.springframework.aop.framework.ProxyFactory;

/**
 * @author guanhao02
 * @date 2022/7/18
 */
public class Proxy {

    interface Pojo {
        void foo();
        void bar();
    }
    static public class SimplePojo implements Pojo {
        @Override
        public void foo() {
            this.bar();
        }
        @Override
        public void bar() {}
    }
    static public class SimplePojo2 {
        public void foo() {
            this.bar();
        }
        public void bar() {}
    }

    public static void main(String[] args) {
        ProxyFactory factory1 = new ProxyFactory(new SimplePojo());
        factory1.addInterface(Pojo.class);
        Pojo pojo1 = (Pojo) factory1.getProxy();
        // this is a method call on the proxy!
        pojo1.foo();

        ProxyFactory factory2 = new ProxyFactory(new SimplePojo2());
        factory2.setExposeProxy(true);
        SimplePojo2 pojo2 = (SimplePojo2) factory2.getProxy();
        // this is a method call on the proxy!
        pojo2.foo();

        System.out.println();
    }

}
