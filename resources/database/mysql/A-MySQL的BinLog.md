> MySQL官方文章 https://dev.mysql.com/doc/internals/en/binary-log.html

# BinaryLog 二进制日志
我们简称BinLog，它属于 MySQL 级别，和存储引擎没有关系，记得和InnoDB的redoLog、undoLog区分开

# CMD
```
#查看binlog列表
show binary logs; 
#查看最新的binlog 
show master status; 
#查看日志内容
show binlog events in 'binlog.000035'; 
#手动刷盘
flush logs; 
```
