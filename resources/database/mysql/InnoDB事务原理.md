## 事务
事务具有ACID四个特性。也即：原子性（atomicity）、一致性（consistency）、隔离性（isolation，独立性）、持久性（durability）。

## InnoDB怎么实现的事务。
ACD三个特性是通过 Redo log 和 Undo log 实现的。隔离性是通过锁来实现的。

### RedoLog
RedoLog用来实现事务的durability-持久性 
1. 内存中的重做日志缓冲
2. 重做日志文件  
一看有内存和磁盘上的两个对应实体，我们就知道这样做一定是为了效率考虑，因为内存的读写效率要比磁盘读写效率高太多。  
> 流程：事务日志 - redo log buffer（内存缓存） - redo log file（操作系统缓存） - fsync（刷到磁盘）  

在事务提交时，必须先将该事务的所有日志写入到redo日志文件中，待事务的commit操作完成才算整个事务操作完成。
在每次将redo log buffer写入redo log file后，都需要调用一次fsync操作，
因为重做日志缓冲只是把内容先写入操作系统的缓冲系统中，并没有确保直接写入到磁盘上，所以必须进行一次fsync操作。
因此，磁盘的性能在一定程度上也决定了事务提交的性能。

关于fsync这个操作用户是可以干预的，因为每次提交事务都执行一次fsync，确实影响数据库性能。  
通过innodb_flush_log_at_trx_commit来控制redo log刷新到磁盘的策略。
1. 默认值为1，表示每次提交事务时都执行一次fsync操作。
2. 0则表示事务提交时不进行写入重做日志文件，这个写入操作由master thread进程来完成，master thread每一秒会进行一次重做日志文件的fsync操作。
3. 2则表示事务提交时将重做日志写入重做日志文件，但仅写入文件系统的缓存中，并不进行fsync操作。  
用户可以通过设置0或者2啦提高事务提交的性能，也可以设置1来要求确保redo log是写入文件中的，总之三种方法各有利弊。

redo log buffer将内存中的log block刷新到磁盘是有一定的规则的：事务提交时(前面已经提到)、当log buffer中有一半的内存空间被使用时、log checkpoint时。

### UndoLog
1. 实现事务回滚
2. 实现MVCC  
undo log和redo log记录物理日志不一样，它是逻辑日志。
可以认为当delete一条记录时，undo log中会记录一条对应的insert记录，反之亦然，当update一条记录时，它记录一条对应相反的update记录。  
当执行回滚时，就可以从undo log中的逻辑记录读取到相应的内容并进行回滚。  
有时候应用到行版本控制的时候，也是通过undo log来实现的：当读取的某一行被其他事务锁定时，它可以从undo log中分析出该行记录以前的数据是什么，从而提供该行版本信息，帮助用户实现一致性非锁定读取。
