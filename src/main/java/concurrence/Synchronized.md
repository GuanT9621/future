# Synchronized
synchronized实现同步的基础： Java中的每一个对象都可以作为锁！  
1. 普通方法  锁当前实例 this
2. 静态方法  锁类的Class对象
3. 方法块    锁synchronized(monitor)括号内的对象

每一个对象都可以作为锁： 锁信息存在Java对象头里。
1. 数组对象    对象头3个字宽 （64位JVM里 1字宽=8字节 即64bit） 
2. 非数组对象  对象头2个字宽 （32位JVM里 1字宽=4字节 即32bit）

### Jvm实现
代码块同步基于 monitor_enter 指令和 monitor_exit 指令实现  
任何对象都有个 monitor 与之关联；线程获取对象的锁，即获取对象的 monitor 的所有权，在执行到 monitor_enter 指令时。

### 锁升级（不能降级）
无锁 - 偏向锁 - 轻量级锁 - 重量级锁
1. 偏向锁：
   1. 加锁：当一个线程访问同步块并获取锁时，会在对象头和栈帧中的锁记录里存储锁偏向的线程ID，  
      以后该线程在进入好喝退出同步块时不需要进行CAS来加锁解锁，只需简单测试下对象头里的MarkWord是否存储着指向当前线程的偏向锁。
   2. 撤销：在全局安全点，暂停线程，讲MarkWord设置为空，回复线程。
   3. jvm参数
      1. 关闭延迟激活偏向锁-XX:BiasedLockingStartupDelay=0 
      2. 关闭偏向锁-XX:-UseBiasedLocking=false
2. 轻量级锁
   1. 加锁：在执行到monitor_enter时，JVM会在当前线程的栈帧中创建一个空间来存储锁记录，讲对象头中的MarkWord复制到锁记录中，
      官方称为Displaced MarkWord（为了对比），然后线程尝试CAS讲对象头的MarkWord替换为指向锁记录的指针，成功就获得锁，失败则自旋。
   2. 撤销：使用CAS将Displaced MarkWord替换回到对象头，如果成功说明没有竞争，失败则存在竞争，锁膨胀为重量级。
3. 重量级锁
   1. 加锁：在存在竞争时，则挂起线程，进入阻塞队列，等待被唤醒。