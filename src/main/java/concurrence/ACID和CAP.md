#ACID
ACID，是指在数据库管理系统（DBMS）中，事务(transaction)所具有的四个特性：原子性（Atomicity）、一致性（Consistency）、隔离性（Isolation，又称独立性）、持久性（Durability）。

#CAP
一致性(Consistency) 、可用性 (Availability) 、分区容忍性(Partition tolerance)。CAP原理是，这三个要素最多只能同时实现两点，不可能三者兼顾。
这是Brewer教授于2000年提出的，后人也论证了CAP理论的正确性。

#BASE
BASE全称是BasicallyAvailable（基本可用）, Soft-state（软状态/柔性事务）, Eventually Consistent（最终一致性）。
BASE模型在理论逻辑上是相反于ACID模型的概念，它牺牲高一致性，获得可用性和分区容忍性。

##举例
一个服务需要写redis和mysql
先更新数据库，再更新缓存
先删除缓存，再更新数据库
先更新数据库，再删缓存（最优）
1. Redis和mysql可以认为是一个分布式系统了，所以cap三选二你选吧
2. 老样子大家要p，那就是ca二选一。一般是ap，所以脏数据不可避免
3. ap就是延时双删和先写后删，但是都有可能删失败，这时候expire就有用了，他保证了最终一致性
4. 要cp的话疯狂retry直到成功，在这之前redis不可用





    参考来源 https://blog.csdn.net/u010039929/article/details/70173119
