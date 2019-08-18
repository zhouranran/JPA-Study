package cn.com.star.cache;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", time=" + time +
                ", createTime=" + createTime +
                ", text='" + text + '\'' +
                '}';
    }
}
