# JPA-Study-CRUD
1：JPA核心API简介
    
    /**
     * 核心API的简介
     * Persistence   主要是创建EntityManagerFactory, 根据传入的   <persistence-unit name="cn.com.star">名称来创建相应的factory
     * EntityManagerFactory  EntityManger 工厂负责创建EntityManager
     * EntityManager    实体管理对象 调用具体的 persist merge remove find findAll
     * EntityTransaction 事务管理对象 
     * EntityTransaction 为javax.persistence.EntityTransaction
     * 此类只能空值同一数据库不同表，大多数都使用这各类
     * JTA Transaction 为javax.transaction.Transaction
     * 此类可以处理不同数据库的事务。 事务列表list, 当list为true时提交事务
     */
     
2：JPA的简单注解使用

    @Entity
    //希望把Employee变成一张数据库表
    @Table(name = "t_employee")
    // 配置表名，如果没有写@Table，默认使用类名做为表名
    public class Employee {
        @Id
        //配置主键
        //配置主键生成策略  @GeneratedValue(Strategy=GenerationType.AUTO) 默认值
        //mysql:AUTO_INCREMENT自增
        //oracle:序列
        @GeneratedValue
        //修改数据库列名
        //唯一约束 UNIQUE_KEY unique = true
        // 非空约束 nullable = false
        @Column(name = "e_id", unique = true, nullable = false)
        private long id;
        private String name;
        //修改字段的长度
        @Column(name = "pwd", length = 20)
        private String password;
    
        private Integer age;
        private BigDecimal salary;
        private Boolean sex;
        //出生：年月日
        @Temporal(TemporalType.DATE)
        private Date birthday;
        //会议时间： 时分秒
        @Temporal(TemporalType.TIME)
        private Date time;
        //注册时间： 年月日 时分秒 默认生成时间戳
        @Temporal(TemporalType.TIMESTAMP)
        //注册时间不能被修改
        @Column(updatable = false)
        private Date createTime;
        //大文本 oracle clob mysql longtext postgres text
        @Lob
        private String text;
     }   

3： JPA主键生成策略
    
    1: 四种生成策略  auto策略  table策略 sequence策略 identity策略
    
    主键实现的两种类型
    1: 自然主键 有业务意义的主键
    2: 代理主键 这个主键没有意义，用来区分数据库每行记录