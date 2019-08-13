# lambda-star

1：lambda的简单使用

     lambda 是JDK1.8的新特性，它的简单表达如下：
      * {parameters}-> expression (表达式方式)
      * {parameters}-> {statements;} (语句块)
     
     lambda 的重要特性:
      * 可选类型声明: 不需要声明参数类型， 编译器会统一识别参数类型
      * 可选的参数圆括号：一个参数无需定义圆括号但是多个参数是需要定义圆括号
      * 可选的大括号： 如果主体只包含一个语句，就不需要使用大括号
      * 可选的返回关键字： 如果主体只有一个表达式返回值则编译器会自动返回值
        
2：lambda的一些示例
     
     lambda 的一些简单场景应用:
      * Thread new Runnable eg:()->{statements;};
      * Vaadin or Swing addListener eg:e->{statements;};
      * Predicate的使用 提供or 或者and method 并传入filter.
      * create a list after filter eg:collect(Collectors.toList())
      * joining the list to string eg:collect(Collectors.joining(", "))
     

3：stream流的方法使用

      * stream()流 是一种来自数据源的元素队列并支持聚合操作
      * 元素:是特定的类型对象，形成一个队列
      * 数据源：流的来源。 可以是集合, 数组, I/O channel
      * 聚合操作: 类似sql语句一样, 如filter/map/reduce/find/match/sorted.
      * pipelining: 中间操作都会返回对象本身
      * 内部迭代： stream 提供内部迭代，通过访问者模式vistor实现

4：lambda在list中典型案例

    lambda 在list中的应用
      * 1： list sorted comparing
      * 2： list filter predicate
      * 3： list 转 map groupingby
      * 4： list 中部分属性转map
      * 5： list 转set

5：lambda在map中的典型案例

    lambda 在map中的应用
      * 1： map 遍历与排序
      * 2： map转list
      * 3： map中提取value 或者key add to list
 
6：lambda 的简单例子

      * 1：找出2011年发生的所有交易，并按交易额排序
      * 2：交易员在哪些不同的城市工作过
      * 3：查找所有来自剑桥的交易员，并按姓名排序
      * 4：返回所有交易员的姓名字符串，并按字母顺序排序
      * 5：有没有交易员在米兰工作的？
      * 6：打印生活在剑桥的交易员的所有交易额
      * 7：所有交易中，最高的交易额是多少
      * 8：交易员Raoul的总额度
