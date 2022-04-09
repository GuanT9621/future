
## 覆盖索引  
如果索引包含所有满足查询需要的数据的索引成为覆盖索引(Covering Index)，也就是平时所说的不需要回表操作  
如：SELECT c1 FROM TEST_TABLE WHERE c1=1  其中 c1 是索引，这时索引包含了查询的数据 c1 就不需要回表，也就是覆盖索引。

## 回表  
在数据中，当查询数据的时候，在索引中查找索引后，获得该行的rowid，根据rowid再查询表中数据，就是回表。  

## 判断标准  
使用explain，可以通过输出的extra列来判断，对于一个索引覆盖查询，显示为using index,MySQL查询优化器在执行查询前会决定是否有索引覆盖查询  

https://www.51cto.com/article/687137.html
https://dev.mysql.com/doc/refman/8.0/en/column-indexes.html