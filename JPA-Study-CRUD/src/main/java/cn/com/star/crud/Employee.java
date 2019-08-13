package cn.com.star.crud;

import javax.persistence.*;

@Entity
//希望把Employee变成一张数据库表
@Table(name = "t_employee")
// 配置表名，如果没有写@Table，默认使用类名做为表名
public class Employee {
    @Id
    //配置主键
    @GeneratedValue
    //配置主键生成策略  @GeneratedValue(Strategy=GenerationType.AUTO) 默认值
    //mysql:AUTO_INCREMENT自增
    //oracle:序列
    @Column(name = "e_id")
    //修改数据库列名
    private long id;
    private String name;
    //修改字段的长度
    @Column(name = "pwd", length = 20)
    private String password;

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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
