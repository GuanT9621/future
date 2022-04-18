# ACID
ACID 模型是一组数据库设计原则，强调对业务数据和关键任务应用程序很重要的可靠性方面。本文以 InnoDB 为例。
## Atomicity 原子性 
ACID 模型的原子性方面主要涉及 InnoDB 事务。功能包括：
* The autocommit setting. 
* The COMMIT statement. 
* The ROLLBACK statement.

## Consistency 一致性
ACID 模型的一致性方面主要涉及内部 InnoDB 处理以保护数据免受崩溃。功能包括：
* InnoDB 双写缓冲区
* InnoDB 崩溃恢复

## Isolation 隔离性
ACID 模型的隔离方面主要涉及 InnoDB 事务，特别是适用于每个事务的隔离级别。功能包括：
* The autocommit setting.
* 事务隔离级别和 SET TRANSACTION 语句。
* InnoDB 锁定的底层细节。可以在 INFORMATION_SCHEMA 表中查看详细信息。

## Durability 持久性
ACID 模型的持久性方面涉及与您的特定硬件配置交互的 MySQL 软件功能。
由于取决于您的 CPU、网络和存储设备的功能有许多可能性，因此提供具体指导方针是最复杂的。 特性包括：
* InnoDB 双写缓冲区。
* innodb_flush_log_at_trx_commit 变量。
* sync_binlog 变量。
* innodb_file_per_table 变量。
* 存储设备中的写入缓冲区，例如磁盘驱动器、SSD 或 RAID 阵列。
* 存储设备中的电池支持缓存。
* 用于运行 MySQL 的操作系统，特别是它对 fsync() 系统调用的支持。
* 不间断电源 (UPS) 保护运行 MySQL 服务器和存储 MySQL 数据的所有计算机服务器和存储设备的电力。
* 您的备份策略，例如备份频率和类型，以及备份保留期。
* 对于分布式或托管数据应用程序，MySQL 服务器硬件所在的数据中心的特定特征，以及数据中心之间的网络连接。