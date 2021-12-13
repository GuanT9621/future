## todo 20220130

AQS共享模式和独占模式的实现上最大的差别就在于共享模式获取锁后的传播。

## 一、理解 AQS 里的 state 
注释里只有一句 The synchronization state. 说她是同步的状态。  
有这三种方法：
> int getState()  
> void setState(int newState)  
> boolean compareAndSetState(int expect, int update)  

释义：
一个锁中只有一个state状态，当state为0时，代表所有线程没有获取锁，当state为1时，代表有线程获取到了锁。
通过CAS是否能把state从0设置成1，代表一个线程是否获取锁成功。如果是共享锁，那么就将state加1。

state属性的总结：
1. 因为存在多线程竞争的情形，使用CAS设置值
2. state初始值为0，线程加一次锁，state加1，获得锁的线程再次加锁，state值再次加1。所以state表示已获得锁线程进行lock操作的次数
3. 是volatile修饰的变量，线程直接从主存中读

## 二、理解 AQS 里 Node 的四种 waitState

* static final int CANCELLED =  1; waitStatus value to indicate thread has cancelled
* static final int SIGNAL    = -1; waitStatus value to indicate successor's thread needs unparking
* static final int CONDITION = -2; waitStatus value to indicate thread is waiting on condition
* static final int PROPAGATE = -3; waitStatus value to indicate the next acquireShared should unconditionally propagate

1  取消状态，当线程不再希望获取锁时，设置为取消状态
-1 当前节点的后继者处于等待状态，当前节点的线程如果释放或取消了同步状态，通知后继节点
-2 等待队列的等待状态，当调用signal()时，进入同步队列
-3 共享模式，同步状态的获取的可传播状态

## 三、加锁
加锁简单来说就是让线程排队，可能会反复阻塞和取消阻止，调用 tryAcquire 直到成功，然后返回。  
sync.acquire(1);  
sync.acquireShared(1);  

共享锁的获取总结如下：
    1. 尝试获取共享锁，如果当前是共享锁或无锁，设置共享锁的state,获取锁
    2. 如果当前是写锁，进入等待流程
    3. 入队，加入等待队列的末尾，成为tail节点
    4. 判断当前节点的前一个节点是不是头节点，如果是的话尝试获取锁，如果获取到了就执行
    5. 获取不到或前一个节点不是头节点就代表该线程需要暂时等待，直到被叫醒为止。设置前一个节点为SIGNAL状态，然后进入等待
    6. 如果可以获取到锁，设置头节点并进入共享锁节点传播流程

## 四、释放锁
释放锁简单来说就是state减一，然后唤起队列下一个线程。  
sync.release(1);  
sync.releaseShared(1);  
