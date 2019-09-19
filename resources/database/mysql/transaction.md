https://www.cnblogs.com/jpfss/p/9450035.html
https://blog.csdn.net/Somhu/article/details/78775198

       隔离级别                     脏读      不可重复读     幻读 
       ===========================================================
       
       未提交读（Read uncommitted）   会           会        会
       
       已提交读（Read committed）    不会          会        会
       
       可重复读（Repeatable read）   不会         不会       会
       
       可串行化（Serializable）      不会         不会       不会

解释  
未提交读（Read uncommitted）  
允许脏读，也就是可能读取到其他会话中未提交事务修改的数据

已提交读（Read committed）   
只能读取到已经提交的数据。Oracle等多数数据库默认都是该级别 (不重复读)

可重复读（Repeatable read）   
在同一个事务内的查询都是事务开始时刻一致的，InnoDB默认级别。在SQL标准中，该隔离级别消除了不可重复读，但是还存在幻象读  
幻读：幻读指的是一个事务在前后两次查询同一个范围的时候，后一次查询看到了前一次查询没有看到的行。   
如A，B同时开始事务，A插入了line1结束事务，B读到的数据不受A的影响，B认为未插入line1，B去插入，却报重复了。

可串行化（Serializable）   
完全串行化的读，每次读都需要获得表级共享锁，读写相互都会阻塞