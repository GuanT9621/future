# ConcurrentHashMap 
## 1.7 
segment 分段上锁，存在最大并发度

## 1.8

1、1.8中ConcurrentHashMap的get操作全程不需要加锁
2、get操作全程不需要加锁是因为Node的成员val是用volatile修饰的和数组用volatile修饰没有关系。
3、数组用volatile修饰主要是保证在数组扩容的时候保证可见性。
