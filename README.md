# jpa-study

1：JPA (Java Persist API)

    * Hibernate 是一个开放源代码的对象关系映射（ORM）框架，它是对于JDBC进行了轻量级的封装。 它将entity与数据库表建立关系映射。
    它可以自动生成SQL语句，自动执行，使java程序员可以随心所欲的使用对象思维来操作数据库。
    
    * JPA 是SUN公司官方提出的java持久化规范，它的出翔主要是简化持久化开发整合结束了现在 Hibernate、TopLink（Eclipse Link）等ORM框架各自为营的局面。
    
    JPA是一种规范，而Hibernate是它的实现。
        
2：JPA 的优缺点
     
     JDBC的优缺点:
      *优点：操作数据库最底层，性能高。
      *缺点：
         1： 使用复杂。
         2： 移植数据库比较麻烦，改动比较多。（主键的生成方式不同）。
         3： 性能优化得自己处理。
         4： 面向sql语句，不是面向对象
         
      JPA的优缺点：处理Java对象和关系层数据库表之间的转化，只是对JDBC的一个简单的封装。
       *优点： 
         1： 程序员操作简单，代码简单。
         2：直接面向持久对象操作。
         3：提供世界级数据库缓存 （一级缓存、二级缓存、查询缓存）
         4：数据库移植性强 很少修改（通过配置方言）
       *缺点：
         1： 不能干预sql语句的生成。
         2： 一个项目，如果对sql语句的优化比较高，不适合使用JPA
         3： 如果一张表中有上亿级别的数据量，也不适合JPA，也不适用JDBC。（数据库读写分离）

3：ORM的概念

      面向对象概念          面向关系概念
      类                   表
      对象实体             表的行（记录）
      属性                 表的列
      
4：持久化配置 META-INFO文件夹下
    
       <!--持久化名称 -->
          <persistence-unit name="cn.com.star">
              <properties>
                  <!-- 必须配置的4个连接信息-->
                  <property name="hibernate.connection.url" value="jdbc:postgresql://localhost/company" />
                  <property name="hibernate.connection.driver_class" value="org.postgresql.Driver" />
                  <property name="hibernate.connection.username" value="postgres" />
                  <property name="hibernate.connection.password" value="sql" />
      
                  <!--必须配置一个方言属性 -->
                  <!-- 方言的作用： 主键的选择， 分页的sql的处理 不同的数据库需要不同的方言去解释-->
                  <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQL94Dialect" />
      
      
                  <!--可选配置-->
                  <!--是否显示sql -->
                  <property name="hibernate.show_sql" value="true" />
                  <!--是否自动建表 hbm 映射文件 ddl建表语句-->
                  <!-- 自动生成表策略-->
                  <!--
                      1:create-drop 先删除表-再建表-再删除表  删除时必须把EntityManagerFactory关闭 （一般不会用）
                      2:create 先删除表再建立表 （在测试环境中经常被应用）
                      3:update 如果没有表根据映射信息创建表
                      如果表里已经有这个属性，更改属性长度，不会更新到表里面 （测试和web项目中）
                      4:validate 用在系统上线或者给定了数据库
                      表不存在，会抛出异常
                  -->
                  <property name="hibernate.hbm2ddl.auto" value="create" />
                  <!--是否格式化sql -->
                  <property name="hibernate.format_sql" value="false" />
              </properties>
          </persistence-unit>