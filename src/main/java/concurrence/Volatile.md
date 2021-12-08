# volatile
1. 保证内存可见性
2. 禁止指令重排
volatile是轻量级的 synchronized，他在多处理器开发中保证了共享变量的可见性。  
可见性的意思是当一个线程修改一个共享变量是，另一个线程能读到这个修改的值。  

### 保证内存可见性 - lock
汇编里的 lock 
volatile修饰的共享变量进行写操作时，会多出第二行汇编代码 lock addl $0x0，而lock前缀指令会：   
1 将当前处理器缓存行的数据写回系统内存  
2 这个写回内存的操作会使在其他CPU里缓存了该内存地址的数据无效。参考缓存一致性协议  
LOCK# 信号以前锁总线，现在锁缓存，毕竟锁总线消耗太大。  

*优化实例：*  
Doug lea追加对象到64字节的方式来填满高速缓冲区的缓存行，避免头节点和尾节点加载到同一个缓存行，  
两者修改时不会互相锁定。注意只在处理器L1 L2 L3缓存的高速缓存行是64个字节宽，不支持部分填充缓存行时。
参考 java.util.concurrent.LinkedTransferQueue.LinkedTransferQueueTest

缓存一致性协议：每个处理器通过嗅探在总线上传播的数据来检查自己缓存的值是不是过期了。

### 禁止指令重排 - 内存屏障
如果一个操作不是原子的，就会给JVM留下重排的机会。  
线程A中 { context = loadContext(); inited = true; }
线程B中 { if (inited) {fun(context);} }  
如果线程A中的指令发生了重排序，那么B中很可能就会拿到一个尚未初始化或尚未初始化完成的context,从而引发程序错误。

#### 禁止指令重排的原理  
volatile关键字提供内存屏障的方式来防止指令被重排，编译器在生成字节码文件时，会在指令序列中插入内存屏障来禁止特定类型的处理器重排序。  
JVM内存屏障插入策略：
1. 在每个volatile写操作的前面插入一个StoreStore屏障； 
2. 在每个volatile写操作的后面插入一个StoreLoad屏障； 
3. 在每个volatile读操作的前面插入一个LoadLoad屏障； 
4. 在每个volatile读操作的后面插入一个LoadStore屏障。

