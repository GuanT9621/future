
#Paxos
https://zhuanlan.zhihu.com/p/31780743  
Paxos算法是Lamport提出的一种基于消息传递的分布式一致性算法，使其获得2013年图灵奖。


#Raft
https://www.cnblogs.com/xybaby/p/10124083.html  
Raft算法的头号目标就是容易理解，Raft增强了可理解性，在性能、可靠性、可用性方面是不输于Paxos的。  
raft协议中，一个节点任一时刻处于以下三个状态之一： leader follower candidate