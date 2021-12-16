## Lambda 表达式是一个匿名函数
Java 编译器编译 Lambda 表达式时，会将其转换为类的私有方法，再进行动态绑定。

编译器会根据Lambda表达式生成一个私有的静态函数：
    private static void lambda$main$0(java.lang.String);
