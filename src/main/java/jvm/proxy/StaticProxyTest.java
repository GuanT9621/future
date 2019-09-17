package jvm.proxy;

/**
 * 静态代理(类似于装饰者模式)
 * 静态代理在使用时,需要定义接口或者父类,被代理对象与代理对象一起实现相同的接口或者是继承相同父类.
 *
 * 下面举个案例来解释:
 * 模拟保存动作,定义一个保存动作的接口:IUserDao.java,然后目标对象实现这个接口的方法UserDao.java,此时如果使用静态代理方式,就需要在代理对象(UserDaoProxy.java)中也实现IUserDao接口.调用的时候通过调用代理对象的方法来调用目标对象.
 * 需要注意的是,代理对象与目标对象要实现相同的接口,然后通过调用相同的方法来调用目标对象的方法
 */
public class StaticProxyTest {
    /**
     * 接口
     */
    interface IUserDao {
        void save();
    }
    /**
     * 接口实现
     * 目标对象
     */
    static class UserDao implements IUserDao {
        public void save() {
            System.out.println("----已经保存数据!----");
        }
    }
    /**
     * 代理对象,静态代理
     */
    static class UserDaoProxy implements IUserDao{
        //接收保存目标对象
        private IUserDao target;
        public UserDaoProxy(IUserDao target){
            this.target=target;
        }

        public void save() {
            System.out.println("开始事务...");
            target.save();//执行目标对象的方法
            System.out.println("提交事务...");
        }
    }

    public static void main(String[] args) {
        //目标对象
        UserDao target = new UserDao();

        //代理对象,把目标对象传给代理对象,建立代理关系
        UserDaoProxy proxy = new UserDaoProxy(target);

        proxy.save();//执行的是代理的方法
    }
}
