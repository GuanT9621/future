# ConcurrentHashMap
线程安全
resize  线程安全
read    线程安全，共享变量（主要是 node.value）都使用 volatile 修饰，volatile 可以保证内存可见性，所以不会读取到过期数据。
write   线程安全，加锁

Ps：
1.8中
Node.value  用volatile修饰主要是为了保证 get 操作不加锁
数组table   用volatile修饰主要是保证在数组扩容的时候保证可见性。

## 1.7
底层数据结构是数组加链表，再加上分段 segment（默认16个）
segment 分段上锁，存在最大并发度 16 

## PUT操作
1. 定位 Segment 并确保定位的 Segment 已初始化；
2. 调用 Segment 的 put 方法；
   1. 尝试获取对象锁，如果获取到返回 true，否则执行scanAndLockForPut方法，这个方法也是尝试获取对象锁； 
   2. 获取到锁之后，类似 hashMap 的 put 方法，通过 key 计算所在 HashEntry 数组的下标； 
   3. 获取到数组下标之后遍历链表内容，通过 key 和 hash 值判断是否 key 已存在，如果已经存在，通过标识符判断是否覆盖，默认覆盖； 
   4. 如果不存在，采用头插法插入到 HashEntry 对象中； 
   5. 最后操作完整之后，释放对象锁；



## 1.8
底层数据结构是数组 + 链表（6）+ 红黑树（8）

table 数组 index 要更新的数组元素索引 expect 预期值 update 要更新写入的值
U.compareAndSwapObject(table, index, expect, update)
为什么 expect 都是null，因为如果不是 null，那么加锁取出元素Node，更新node.value即可。

## PUT操作
1. 首先会判断 key、value 是否为空，如果为空就抛异常！ 
2. 接着会判断容器数组是否为空，如果为空就初始化数组； 
3. 进一步判断，要插入的元素f，在当前数组下标是否第一次插入，如果是就通过 CAS 方式插入； 
4. 在接着判断f.hash == -1是否成立，如果成立，说明当前f是ForwardingNode节点，表示有其它线程正在扩容，则一起进行扩容操作； 
5. 其他的情况，就是把新的Node节点按链表或红黑树的方式插入到合适的位置； 
6. 节点插入完成之后，接着判断链表长度是否超过8，如果超过8个且数组大于64，就将链表转化为红黑树结构； 
7. 最后，插入完成之后，进行扩容判断；

## initTable初始化数组
sizeCtl = 0; // force exclusion for table construction
sizeCtl 是一个对象属性，使用了 volatile 关键字修饰保证并发的可见性，默认为 0，
当第一次执行 put 操作时，通过Unsafe.compareAndSwapInt()方法，俗称CAS，将 sizeCtl修改为 -1，有且只有一个线程能够修改成功，接着执行 table 初始化任务。
如果别的线程发现sizeCtl<0，意味着有另外的线程执行 CAS 操作成功，当前线程通过执行Thread.yield()让出 CPU 时间片等待 table 初始化完成。



